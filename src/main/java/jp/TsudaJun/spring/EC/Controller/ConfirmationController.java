package jp.TsudaJun.spring.EC.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmationController {
	
	@RequestMapping(value ="/confirmation", method=RequestMethod.GET)
	public ModelAndView show(
			HttpServletRequest request,
			ModelAndView mav) {
		
		mav.setViewName("confirmation");
		
		return mav;
	}

}
