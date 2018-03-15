package jp.TsudaJun.spring.EC.Controller;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;

@Controller
public class MyItemDetailController {
	
	@Autowired
	ItemDao iDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/myitemdetail/{itemid}", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			@PathVariable("itemid") int itemid,
			ModelAndView mav){
		
		mav.setViewName("myitemdetail");
		Item item = iDao.getItemById(itemid);
		if(!item.getUserid().equals(principal.getName())) {
			mav = new ModelAndView("redirect:/error");
			return mav;
		}
		mav.addObject("item", item);
		mav.addObject("imgPath", imgPath);
		return mav;
	}
	
	@RequestMapping(value ="/myitemdetail/{itemid}", method=RequestMethod.POST)
	public ModelAndView register(
			ModelAndView mav,
			@Validated @ModelAttribute("item") Item item,
			BindingResult result,
			@PathVariable("itemid") int itemid,
			Principal principal) {
		mav.setViewName("myitemdetail");
		if(!result.hasErrors()) {
			item.setItemid(itemid);
			BigDecimal price_bd = new BigDecimal(String.valueOf(item.getPrice()));
			BigDecimal tax_rate = new BigDecimal(0.08);
			BigDecimal tax = price_bd.multiply(tax_rate).setScale(0, BigDecimal.ROUND_HALF_UP);
			BigDecimal includingtax_bd = price_bd.add(tax);
			item.setTax(tax.intValue());
			item.setIncludingtax(includingtax_bd.intValue());
			item.setUserid(principal.getName());
			iDao.merge(item);
			iDao.close();
			mav = new ModelAndView("redirect:/kanryo/?msg=myitemdetail");
		}else{
			mav.addObject("msg","エラーが発生しました");
			mav.addObject("imgPath", imgPath);
		}
		
		return mav;
	}

}
