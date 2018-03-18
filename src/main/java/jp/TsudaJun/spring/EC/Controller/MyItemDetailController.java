package jp.TsudaJun.spring.EC.Controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.ItemAttribute;

@Controller
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class MyItemDetailController {
	
	@Autowired
	ItemDao iDao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@Value("${img.path}")
	private String imgSavePath;
	
	@RequestMapping(value ="/myitemdetail/{itemid}", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			@PathVariable("itemid") int itemid,
			ModelAndView mav){
		
		mav.setViewName("myitemdetail");
		Item item = iDao.getItemById(itemid);
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		if(!item.getUserid().equals(principal.getName())) {
			mav = new ModelAndView("redirect:/error");
			return mav;
		}
		mav.addObject("item", item);
		mav.addObject("imgPath", imgPath);
		mav.addObject("attributes", attributes);
		return mav;
	}
	
	@RequestMapping(value ="/myitemdetail/{itemid}", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ModelAndView register(
			ModelAndView mav,
			@Validated @ModelAttribute("item") Item item,
			BindingResult result,
			@PathVariable("itemid") int itemid,
			@RequestParam MultipartFile file,
			Principal principal) throws IllegalStateException, IOException {
		mav.setViewName("myitemdetail");
		if(!result.hasErrors()) {
			//ID
			item.setItemid(itemid);
			//includingtax
			BigDecimal price_bd = new BigDecimal(String.valueOf(item.getPrice()));
			BigDecimal tax_rate = new BigDecimal(0.08);
			BigDecimal tax = price_bd.multiply(tax_rate).setScale(0, BigDecimal.ROUND_HALF_UP);
			BigDecimal includingtax_bd = price_bd.add(tax);
			item.setTax(tax.intValue());
			item.setIncludingtax(includingtax_bd.intValue());
			//画像
			if (file.getSize() > 0 && file.getContentType().equals("image/jpeg") ) {
				File imageFile = new File(imgSavePath + item.getItemid() +".jpg");
				file.transferTo(imageFile);
			}
			//userid
			item.setUserid(principal.getName());
			//merge
			iDao.merge(item);
			iDao.close();
			mav = new ModelAndView("redirect:/kanryo/?msg=myitemdetail");
		}else{
			List<ItemAttribute> attributes = iaDao.getAllAttributes();
			mav.addObject("attributes", attributes);
			mav.addObject("msg","エラーが発生しました");
			mav.addObject("imgPath", imgPath);
		}
		
		return mav;
	}

}
