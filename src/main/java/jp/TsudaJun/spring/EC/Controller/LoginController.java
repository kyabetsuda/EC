package jp.TsudaJun.spring.EC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value ="/login", method=RequestMethod.GET)
	public ModelAndView show(
			ModelAndView mav){
		
		mav.addObject("msg", "ログインページ");
		mav.setViewName("login");
		return mav;
	}

}
