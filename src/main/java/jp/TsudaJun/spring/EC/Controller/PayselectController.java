package jp.TsudaJun.spring.EC.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PayselectController {
	
	@RequestMapping(value ="/payselect", method=RequestMethod.GET)
	public ModelAndView show(
			ModelAndView mav) {
		
		mav.setViewName("payselect");
		mav.addObject("msg", "支払い方法選択");
		
		return mav;
	}
	
	@RequestMapping(value ="/payselect", method=RequestMethod.POST)
	public ModelAndView choose(
			@RequestParam("howtopay") String howtopay,
			HttpServletRequest request,
			ModelAndView mav) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		
		session.setAttribute("howtopay", howtopay);
		mav = new ModelAndView("redirect:/confirmation");
		
		return mav;
	}

}
