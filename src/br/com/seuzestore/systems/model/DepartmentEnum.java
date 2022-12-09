package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum DepartmentEnum {
	
	VERAO("VER", "MODA VERÃO"),
	INVERNO("INV", "MODA INVERNO"),
	INTIMA("INT", "MODA INTIMA"),;
	
	private String reference, desc;
	
	static final private Map<String, DepartmentEnum> mapDepEnum = new HashMap<>();
	
	static {
		for(DepartmentEnum dep : DepartmentEnum.values()) {
			mapDepEnum.put(dep.getReference(), dep);
		}
	}
	
	

	DepartmentEnum(String ref, String desc) {
		this.reference = ref;
		this.desc = desc;
		
	}

	public String getReference() {
		return reference;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Boolean checkDepartment(String ref) {
		Boolean resp;
		
		if(mapDepEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static DepartmentEnum getDepartment(String ref) {

		return mapDepEnum.get(ref);
		 
		 
	}

}
