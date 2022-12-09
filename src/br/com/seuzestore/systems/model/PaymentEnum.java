package br.com.seuzestore.systems.model;

import java.util.HashMap;
import java.util.Map;

public enum PaymentEnum {
	
	DINHEIRO("DIN", "DINHEIRO"),
	CREDITO("CRE", "CRÉDITO"),
	DEBITO("DEB", "DÉBITO"),
	PIX("PIX", "CALÇADO"),;
	
	private String reference, desc;
	
	static final private Map<String, PaymentEnum> mapPayEnum = new HashMap<>();
	
	static {
		for(PaymentEnum pay : PaymentEnum.values()) {
			 mapPayEnum.put(pay.getReference(), pay);
		}
	}

	PaymentEnum(String ref, String desc) {
		this.reference = ref;
		this.desc = desc;
	}

	public String getReference() {
		return reference;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Boolean checkPayment(String ref) {
		Boolean resp;
		
		if( mapPayEnum.containsKey(ref))
			 resp = true;
		 else
			 resp = false;
		
		return resp;
			 
		 
	}
	
	public static PaymentEnum getPayment(String ref) {

		return  mapPayEnum.get(ref);
		 
		 
	}


}
