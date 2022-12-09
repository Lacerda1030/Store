package br.com.seuzestore.systems.services;

import java.util.Map;

public interface ServicesInterface {

	Integer getNewCod();
	
	Boolean insert(Object obj);
	
	Boolean refresh(Object obj);
	
	Boolean delete(Integer cod);
	
	 Map<Integer, Object> getList();
	
	
	
}
