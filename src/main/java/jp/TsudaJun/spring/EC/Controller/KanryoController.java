package jp.TsudaJun.spring.EC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KanryoController {
	
	@RequestMapping(value ="/kanryo", method=RequestMethod.GET)
	public ModelAndView show(
			@RequestParam("msg") String msg,
			ModelAndView mav) {
		
		String message = null;
		mav.setViewName("kanryo");
		if(msg.equals("myitemdetail")) {
			message = "商品情報が登録されました";
		}else if(msg.equals("userregister")) {
			message = "ユーザーが登録されました";
		}else if(msg.equals("itemregister")) {
			message = "商品が登録されました";
		}else if(msg.equals("userinfo")) {
			message = "ユーザー情報が登録されました";
		}
		mav.addObject("msg", message);
		return mav;
	}

}
