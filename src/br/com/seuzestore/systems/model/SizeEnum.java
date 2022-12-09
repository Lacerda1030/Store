package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {
	
	PEQUENO("P", "PEQUENO"),
	MEDIO("M", "MÉDIO"),
	GRANDE("G", "GRANDE"),;
	
	private String referencia, desc;
	
	static final private Map<String, SizeEnum> mapSizeEnum = new HashMap<>();
	
	static {
		for(SizeEnum size : SizeEnum.values()) {
			 mapSizeEnum.put(size.getReferencia(), size);
		}
	}

	SizeEnum(String ref, String desc) {
		this.referencia = ref;
		this.desc = desc;
	}


	public String getReferencia() {
		return referencia;
	}

	
	public String getDesc() {
		return desc;
	}

	

	public static Boolean checkSize(String ref) {
		Boolean resp;
		
		if( mapSizeEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static SizeEnum getSize(String ref) {

		return  mapSizeEnum.get(ref);
		 
		 
	}
	
}
