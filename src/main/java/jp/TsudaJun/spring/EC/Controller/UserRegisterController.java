package jp.TsudaJun.spring.EC.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;

@Controller
public class UserRegisterController {
	
	@Autowired
	UserDao dao;
	
	@RequestMapping(value ="/userregister", method=RequestMethod.GET)
	public ModelAndView show(
			@ModelAttribute User user,
			ModelAndView mav){
		mav.addObject("msg", "ユーザー登録");
		mav.addObject("check", true);
		mav.setViewName("userregister");
		return mav;
	}
	
	@RequestMapping(value ="/userregister", method=RequestMethod.POST)
	public ModelAndView register(
			@Validated @ModelAttribute("user") User user,
			BindingResult result,
			ModelAndView mav) {
		if(!result.hasErrors()) {
			dao.persist(user);;
			mav.addObject("msg", "ユーザーが登録されました。");
			mav.addObject("check", false);
		}else{
			mav.addObject("msg","エラーが発生しました");
			mav.addObject("check", true);
		}
		return mav;
	}
}