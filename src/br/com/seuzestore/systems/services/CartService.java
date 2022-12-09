package br.com.seuzestore.systems.services;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.seuzestore.systems.data.CartData;
import br.com.seuzestore.systems.data.StockData;
import br.com.seuzestore.systems.model.Cart;
import br.com.seuzestore.systems.model.Product;

public class CartService implements CartInterface{
	
	CartData cart = new CartData();
	StockData prodData = new StockData();
	ProductService prodServ = new ProductService();
	Map<Integer, Object> mapProdTemp = new LinkedHashMap<>();
	   

	public Integer getNewCod() {
		Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
		int cod = -1;
		
		
		mapCartTemp = getList();
		//prodTemp.setProdTemp();
		if(!mapCartTemp.isEmpty()) {
			for(int k: mapCartTemp.keySet()) {
				if(k>cod && k>0) {
					cod = k;
				}
			}
			cod++;
		}else
			cod=+1;
		
		return cod;
	}

	public Map<Integer, Object> getList() {
		return cart.listItems();
		
	}

	public Boolean insert(Object obj, Object obj2 ) {
		Map<Integer, Object> mapCart = new LinkedHashMap<>();
		Boolean resp = true;
		
		Cart cartTemp;
		Cart cartReceived = (Cart) obj;
		Product pd = (Product) obj2;
		mapCart = getList();
		
		
		if(cartReceived.getDescription() == null && cartReceived.getVlItem() == null) {
			cartTemp = (Cart) mapCart.get(cartReceived.getCodItem());
			
			cartTemp.setQuantity(cartReceived.getQuantity());
			cartTemp.setVlItem(cartReceived.getQuantity()*pd.getPrice());
			cartTemp.setDescricaoo(pd.getDescription());
			cart.updade(cartTemp);
			
		}else {
			cartTemp = (Cart) obj;
			if(cartTemp !=null) {
				cart.save(cartTemp);
				resp = true;
			}else 
				resp = false;
			
		}
		return resp;
		
	}
	
	public Boolean refresh(Integer codItem, Integer codVenda, Integer codProd, Integer quant, Object obj) {
		Map<Integer, Object> mapCart = new LinkedHashMap<>();
		Boolean resp = false;
		Cart cartTemp = new Cart();;
		
		mapCart = getList();
	
		Product prodTemp = (Product) obj;
		
			if(mapCart.containsKey(codItem)) {
				cartTemp = (Cart) mapCart.get(codItem);
				/*if(cartTemp.getCodVenda() == codVenda && cartTemp.getQuantidade() == 0) {
					remove(codItem);
				}*/
				
				cartTemp.setQuantity(quant);
				cartTemp.setVlItem(cartTemp.getQuantity()*prodTemp.getPrice());
				cart.updade(cartTemp);
				resp = true;
			}
			
			return resp;
		}
		
	
			
	public Boolean delete(Integer cod) {
		Map<Integer, Object> mapCart = new LinkedHashMap<>();
		Boolean resp = true;
		mapCart = getList();
				
		Cart cartTemp = (Cart) mapCart.get(cod);
		
		cart.delete(cartTemp);
		resp = true;
			
		return resp;
				
	}

	
	/*public Boolean checkQuant52525(int codItem, int quantItem) {
		Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
		
		Boolean resp = true;
		
		mapCartTemp = getList();
		Cart tempCart = (Cart) mapCartTemp.get(codItem);
		if(quantItem > tempCart.getQuantidade()) {
			resp = false;
		}
		
		return resp;
		
		
	}*/
	
	public Integer locate(int codVenda, int codProd) {
		Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
		
		Integer codItem = 0;
		
		mapCartTemp = getList();
		for(int k : mapCartTemp.keySet()) {
			Cart tempCart = (Cart) mapCartTemp.get(k);
			if(tempCart.getCodSell() == codVenda && tempCart.getCodProd() == codProd)
				codItem = k;
				break;
			
		}
				
		return codItem;
		
	}

	public void debitProd(int codVenda) {
		Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
		
				
		mapCartTemp = getList();
		for(int k : mapCartTemp.keySet()) {
			Cart tempCart = (Cart) mapCartTemp.get(k);
			if(tempCart.getCodSell() == codVenda )
				prodServ.renewQuant(tempCart.getCodProd(), tempCart.getQuantity());
						
		}
	}
	
	public Boolean checkProdSell(Integer cod) {
		Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
		Boolean resp = false;
		mapCartTemp = getList();
		for(Integer k: mapCartTemp.keySet()) {
			Cart cartTemp = (Cart) mapCartTemp.get(k);
			if(cartTemp.getCodProd() == cod) {
				resp = true;
				break;
			}
		}
		return resp;
	}

}
