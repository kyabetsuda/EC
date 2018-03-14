package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;

@Controller
public class MyItemController {
	
	@Autowired
	ItemDao iDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/myitem", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			ModelAndView mav){
		
		mav.setViewName("myitem");
		mav.addObject("msg", "登録した商品");
		List<Item> items = iDao.getItemByUsreid(principal.getName());
		if(items.size() == 0) {
			mav.addObject("check", true);
		}
		mav.addObject("items", items);
		mav.addObject("imgPath", imgPath);
		
		return mav;
	}

}
