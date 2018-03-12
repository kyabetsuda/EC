package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.DAO.SellDao;
import jp.TsudaJun.spring.EC.DAO.UserAddressDao;
import jp.TsudaJun.spring.EC.model.Cart;
import jp.TsudaJun.spring.EC.model.CartItem;
import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.Sell;
import jp.TsudaJun.spring.EC.model.UserAddress;

@Controller
public class ConfirmationController {
	
	@Autowired
	UserAddressDao uaDao;
	
	@Autowired
	SellDao sDao;
	
	@Autowired
	ItemDao iDao;
	
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
			Principal principal,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
		List<CartItem> items = cart.getCartItems();
		
		for(CartItem item : items) {
			
			Sell sell = new Sell();
			sell.setIncludingtax(item.getItem().getIncludingtax() * item.getQuantity());
			sell.setQuantity(item.getQuantity());
			sell.setItemid(item.getItem().getItemid());
			sell.setUserid(principal.getName());
			sell.setAddressid(Integer.parseInt((String) session.getAttribute("addressid")));
			sDao.persist(sell);
			sDao.close();
			Item item_entity  = iDao.getItemById(item.getItem().getItemid());
			item_entity.setStock(item_entity.getStock() - item.getQuantity());
			iDao.merge(item_entity);
		}
		
		session.removeAttribute("cart");
		mav = new ModelAndView("redirect:/bought");
		return mav;
	}

}
