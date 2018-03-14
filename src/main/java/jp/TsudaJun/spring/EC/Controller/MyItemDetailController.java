package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;

@Controller
public class MyItemDetailController {
	
	@Autowired
	ItemDao iDao;
	
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
		return mav;
	}

}
