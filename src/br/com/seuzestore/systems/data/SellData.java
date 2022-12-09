package br.com.seuzestore.systems.data;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.seuzestore.systems.model.Sell;

public class SellData implements DataInterface {
	
	static Map<Integer, Object> mapSell = new LinkedHashMap<>();

	@Override
	public void save(Object obj) {
		Sell sell =(Sell) obj;
		mapSell.put(sell.getCode(),sell);
		
	}

	@Override
	public void updade(Object obj) {
		Sell sell =(Sell) obj;
		mapSell.replace(sell.getCode(),sell);
	}

	@Override
	public void delete(Object obj) {
		Sell sell =(Sell) obj;
		mapSell.remove(sell.getCode(),sell);
		
	}

	@Override
	public Map<Integer, Object> listItems() {
		// TODO Auto-generated method stub
		return mapSell;
	}

	

}
