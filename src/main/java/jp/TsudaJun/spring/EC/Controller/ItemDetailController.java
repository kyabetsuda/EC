package jp.TsudaJun.spring.EC.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Cart;
import jp.TsudaJun.spring.EC.model.Item;

@Controller
public class ItemDetailController {
	
	@Autowired
	ItemDao dao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/itemdetail/{itemid}", method=RequestMethod.GET)
	public ModelAndView show(
			@PathVariable("itemid") int itemid,
			ModelAndView mav) {
		
		mav.setViewName("itemdetail");
		mav.addObject("msg","商品詳細");
		
		Item item = dao.getItemById(itemid);
		if(item != null) {
			mav.addObject("item",item);
			mav.addObject("imgPath",imgPath);
			mav.addObject("check",true);
		}else {
			mav = new ModelAndView("redirect:/error");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value ="/itemdetail", method=RequestMethod.POST)
	public ModelAndView intoCart(
			ModelAndView mav,
			HttpServletRequest request,
			@RequestParam("itemid") int itemid,
			@RequestParam("quantity") int quantity) {
		Item item = dao.getItemById(itemid);
		HttpSession session = request.getSession(false);
		if (session == null){
			session = request.getSession(true);
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			cart.putItem(item, quantity);
			session.setAttribute("cart", cart);
		}else {
			cart.putItem(item, quantity);
			session.setAttribute("cart", cart);
		}
	
		mav.addObject("msg","カートに商品が追加されました");
		mav.addObject("check", false);
		
		return mav;
	}

}