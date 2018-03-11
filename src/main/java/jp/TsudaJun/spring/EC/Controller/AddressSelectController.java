package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserAddressDao;
import jp.TsudaJun.spring.EC.model.UserAddress;

@Controller
public class AddressSelectController {
	
	@Autowired
	UserAddressDao uaDao;
	
	
	@RequestMapping(value ="/addressselect", method=RequestMethod.GET)
	public ModelAndView show(
			HttpServletRequest request,
			Principal principal,
			@ModelAttribute("address") UserAddress address,
			ModelAndView mav) {
		List<UserAddress> addresses = uaDao.getAddressByUserid(principal.getName());
		mav.addObject("addresses", addresses);
		mav.addObject("userid", principal.getName());
		mav.setViewName("addressselect");
		mav.addObject("msg","住所選択");
		
		return mav;
	}
	
	@RequestMapping(value ="/addressselect", method=RequestMethod.POST)
	public ModelAndView register(
			ModelAndView mav,
			Principal principal,
			@Validated @ModelAttribute("address") UserAddress address,
			BindingResult result) {
		
		
		if(!result.hasErrors()) {
			address.setUserid(principal.getName());
			uaDao.persist(address);
			mav = new ModelAndView("redirect:/payselect");
		}else {
			List<UserAddress> addresses = uaDao.getAddressByUserid(principal.getName());
			mav.addObject("addresses", addresses);
			mav.addObject("userid", principal.getName());
			mav.addObject("msg","エラーが発生しました");
		}
		return mav;
	}
	
	@RequestMapping(value ="/addressselect/{addressid}", method=RequestMethod.POST)
	public ModelAndView choose(
			ModelAndView mav) {
		
		mav = new ModelAndView("redirect:/payselect");
		return mav;
	}

}
