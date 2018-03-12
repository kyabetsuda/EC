package jp.TsudaJun.spring.EC.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserAddressDao;
import jp.TsudaJun.spring.EC.model.Cart;
import jp.TsudaJun.spring.EC.model.CartItem;
import jp.TsudaJun.spring.EC.model.UserAddress;

@Controller
public class ConfirmationController {
	
	@Autowired
	UserAddressDao uaDao;
	
	@Value("${img.accessPath}")
	private String accessPath;
	
	@RequestMapping(value ="/confirmation", method=RequestMethod.GET)
	public ModelAndView show(
			HttpServletRequest request,
			ModelAndView mav) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		
		Cart cart = (Cart) session.getAttribute("cart");
		String addressid = (String) session.getAttribute("addressid");
		String howtopay = (String) session.getAttribute("howtopay");
		
		mav.addObject("msg","最終確認");
		
		if(cart == null) {
			mav.addObject("error", true);
			mav.addObject("check", false);
		}else if(cart != null && (addressid == null || howtopay == null)) {
			mav = new ModelAndView("redirect:/payselect");
		}else if(cart != null && addressid != null && howtopay != null){
			List<CartItem> cartItems = cart.getCartItems();
			UserAddress address = uaDao.getAddressByAddressid(Integer.parseInt(addressid));
			mav.addObject("check", true);
			mav.addObject("items", cartItems);
			mav.addObject("address", address);
			mav.addObject("howtopay", howtopay);
			mav.addObject("imgPath", accessPath);
			mav.addObject("total",cart.calcSum());
		}
		
		mav.setViewName("confirmation");
		
		return mav;
	}
	
	@RequestMapping(value ="/confirmation", method=RequestMethod.POST)
	public ModelAndView buy(
			ModelAndView mav,
			HttpServletRequest request) {
		
		
		return mav;
	}

}
