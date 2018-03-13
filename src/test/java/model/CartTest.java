package model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.TsudaJun.spring.EC.model.Cart;
import jp.TsudaJun.spring.EC.model.CartItem;
import jp.TsudaJun.spring.EC.model.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml"})
@WebAppConfiguration
public class CartTest {
	
	Cart cart = new Cart();
	
	@Before
	public void setUp(){
		Item item1 = new Item();
		item1.setItemid(1);
		CartItem cartItem1 = new CartItem(item1, 1);
		
		Item item2 = new Item();
		item2.setItemid(1);
		CartItem cartItem2 = new CartItem(item2, 2);
		
		cart.putItem(cartItem1);
		cart.putItem(cartItem2);

	}
	
	@Test
	public void cartTest() {
		assertEquals(3, cart.getItemById(1).getQuantity());
	}


}
