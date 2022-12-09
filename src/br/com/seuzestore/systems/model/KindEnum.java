package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum KindEnum {
	MASCULINO("MAS", "MASCULINO"),
	FEMININO("FEM", "FEMININO"),
	INFANTIL("INF", "INFANTIL"),;
	
	private String reference, desc;
	
	static final private Map<String, KindEnum> mapKindEnum = new HashMap<>();
	
	static {
		for(KindEnum dep : KindEnum.values()) {
			mapKindEnum.put(dep.getReference(), dep);
		}
	}

	KindEnum(String ref, String desc) {
		this.reference = ref;
		this.desc = desc;
	}

	public String getReference() {
		return reference;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Boolean checkKind(String ref) {
		Boolean resp;
		
		if(mapKindEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static KindEnum getKind(String ref) {

		return mapKindEnum.get(ref);
		 
		 
	}
	
}
