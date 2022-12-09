package br.com.seuzestore.systems.data;

import java.util.Map;

public interface DataInterface {
	void save(Object obj);
	
	void updade(Object obj);
	
	void delete(Object obj);
	
	 Map<Integer, Object> listItems();
	

	

}
