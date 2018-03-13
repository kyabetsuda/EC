package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value ="/login", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			ModelAndView mav){
		
		if(principal == null) {
			mav.addObject("msg", "ログインページ");
			mav.addObject("check", true);
			mav.setViewName("login");
		}else {
			mav.addObject("msg", "ログアウトしてください");
			mav.addObject("check", false);
			mav.setViewName("login");
		}
		return mav;
	}

}
