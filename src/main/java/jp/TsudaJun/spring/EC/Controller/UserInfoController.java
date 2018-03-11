package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;

@Controller
public class UserInfoController {
	
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value ="/userinfo", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			ModelAndView mav){
		mav.addObject("msg", "ユーザー情報編集");
		mav.addObject("check", true);
		mav.setViewName("userinfo");
		User user = dao.getUserById(principal.getName());
		mav.addObject("user", user);	
		
		return mav;
	}
	
	@RequestMapping(value ="/userinfo", method=RequestMethod.POST)
	public ModelAndView register(
			Principal principal,
			@RequestParam("username") String username,
			@RequestParam("address") String address,
			@RequestParam("postcode") String postcode,
			ModelAndView mav) {
		
		User user = dao.getUserById(principal.getName());
		boolean flg = true;
		
		if(username == null || username.equals("")) {
			flg = false;
			mav.addObject("username_null", true);
		}
		
		if(flg) {
			user.setUsername(username);
			dao.merge(user);
			mav.addObject("msg", "ユーザー情報が登録されました。");
			mav.addObject("check", false);
		}else {
			mav.addObject("user",user);
			mav.addObject("msg","エラーが発生しました");
			mav.addObject("check", true);
		}
		
		return mav;
	}

}
