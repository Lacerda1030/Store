package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum CollorEnum {


	AZUL("AZU", "AZUL"),
	VERMELHO("VER", "VERMELHO"),
	BRANCO("BRA", "BRANCO"),;
	
	private String reference, desc;
	
	static final private Map<String, CollorEnum> mapCorEnum = new HashMap<>();
	
	static {
		for(CollorEnum cor : CollorEnum.values()) {
			 mapCorEnum.put(cor.getReference(), cor);
		}
	}

	CollorEnum(String ref, String desc) {
		this.reference = ref;
		this.desc = desc;
	}
	
	

	public String getReference() {
		return reference;
	}

	public String getDesc() {
		return desc;
	}

	public static Boolean checkCollor(String ref) {
		Boolean resp;
		
		if( mapCorEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static CollorEnum getCollor(String ref) {

		return  mapCorEnum.get(ref);
		 
		 
	}
	
}
