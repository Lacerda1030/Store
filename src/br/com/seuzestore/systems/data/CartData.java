package br.com.seuzestore.systems.data;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.seuzestore.systems.model.Cart;


public class CartData implements DataInterface{
	
	Map<Integer, Object> mapCart = new LinkedHashMap<>();

	@Override
	public void save(Object obj) {
		Cart cart =(Cart) obj;
		mapCart.put(cart.getCodItem(),cart);
		
		
	}

	@Override
	public void updade(Object obj) {
		Cart cart =(Cart) obj;
		mapCart.replace(cart.getCodItem(),cart);
		
	}

	@Override
	public void delete(Object obj) {
		Cart cart =(Cart) obj;
		mapCart.remove(cart.getCodItem(),cart);
		
		
	}

	@Override
	public Map<Integer, Object> listItems() {
		// TODO Auto-generated method stub
		return mapCart;
	}

	
	
	

}






