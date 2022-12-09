package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
	
	ROUPA("ROU", "ROUPA"),
	CALCADO("CAL", "CALÇADO"),
	LINGERIE("LIN", "LINGERIE"),;
	
	private String reference, desc;
	
	static final private Map<String, CategoryEnum> mapCatEnum = new HashMap<>();
	
	static {
		for(CategoryEnum tip : CategoryEnum.values()) {
			 mapCatEnum.put(tip.getReference(), tip);
		}
	}

	CategoryEnum(String ref, String desc) {
		this.reference = ref;
		this.desc = desc;
	}

	public String getReference() {
		return reference;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Boolean checkCategory(String ref) {
		Boolean resp;
		
		if( mapCatEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static CategoryEnum getCategory(String ref) {

		return  mapCatEnum.get(ref);
		 
		 
	}


}
