package br.com.seuzestore.systems.services;

import java.util.Map;

public interface CartInterface {
	
	Integer getNewCod();
	
	Boolean insert(Object obj, Object obj2); 
	
	Boolean refresh(Integer vl1, Integer vl2, Integer vl3, Integer vl4, Object obj);
	
	Boolean delete(Integer cod);
	
	Integer locate(int codSell, int codProd);
	
	void debitProd(int codSell);
	
	Boolean checkProdSell(Integer cod);
	
	Map<Integer, Object> getList();

}
