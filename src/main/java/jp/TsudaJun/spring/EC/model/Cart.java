package jp.TsudaJun.spring.EC.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	
	
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public void putItem(Item item, int quantity) {
		CartItem cartItem = new CartItem(item, quantity);
		this.cartItems.add(cartItem);
	}
	
	public int calcSum() {
		int sum = 0;
		for(CartItem cartItem : cartItems) {
			sum += cartItem.getItem().getIncludingtax() * cartItem.getQuantity();
		}
		
		return sum;
	}
	
	public List<CartItem> getCartItems(){
		return this.cartItems;
	}

}
