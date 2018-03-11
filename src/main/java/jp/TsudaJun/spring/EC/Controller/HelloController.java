package jp.TsudaJun.spring.EC.Controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;

@Controller
public class HelloController {

	@Autowired
	public UserDao dao;
	
	@Value("${message.hello}")  // (2)
	private String message;
	
	@RequestMapping(value ="/hello", method=RequestMethod.GET)
	public ModelAndView show(ModelAndView mav){
		List<User> ret = dao.getAllUsers();
		mav.setViewName("Hello");
		mav.addObject("message", message);
		mav.addObject("datalist", ret);
		
		return mav;
		
	}
}
