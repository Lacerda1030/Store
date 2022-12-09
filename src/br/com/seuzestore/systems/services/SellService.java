package br.com.seuzestore.systems.services;


import java.util.Calendar;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.seuzestore.systems.data.SellData;

import br.com.seuzestore.systems.model.Sell;

public class SellService implements ServicesInterface{
	
	Map<Integer, Object> mapCheckSell = new LinkedHashMap<>();
	Map<Integer, Object> mapCheckCart = new LinkedHashMap<>();
	int codVenda, codItem, codProd, quantItem;
	String paySella, cpf;
	String descriItem;
	Calendar dateVenda;
	Double vlTotSell, vlItem;
	SellData sell = new SellData();
	

	public Integer getNewCod() {
		Map<Integer, Object> mapSellTemp = new LinkedHashMap<>();
		int cod = 0;
		
		
		mapSellTemp = getList();
		if(!mapSellTemp.isEmpty()) {
			for(int k: mapSellTemp.keySet()) {
				if(k>cod && k>0) {
					cod = k;
				}
			}
			cod++;
		}else
			cod=+1;
		
		return cod;
	}
	
	public Boolean insert(Object obj) {
		Boolean resp = false;
		
		Sell tempSell =  (Sell) obj;
		if(!tempSell.equals(null) ) {
			sell.save(tempSell);
			resp = true;
		}
		return resp;
		
	}

	public Boolean refresh(Object obj) {
		Boolean resp = false;
		
			//Sell tempSell = new Sell(codigo, cpf, key ,paymentEnum, dataVenda, vlTotVenda);
		Sell tempSell = (Sell) obj;
		sell.updade(tempSell);
		resp = true;
		return resp;
		
	}
	
	public Boolean delete(Integer cod) {
		Map<Integer, Object> mapSellTemp = new LinkedHashMap<>();
		Boolean resp = false;
		mapSellTemp = getList();
		if(mapSellTemp.containsKey(cod)) {
			Sell tempSell = (Sell) mapSellTemp.get(cod);
			sell.delete(tempSell);
			resp = true;
		}
		return resp;
		
	}
	
	
	public Map<Integer, Object> getList() {
		return sell.listItems();
		
	}


		
	public Map<Integer, Object> getListSellOpen() {
		Map<Integer, Object> mapSellTemp = new LinkedHashMap<>();
		Map<Integer, Object> mapSellOpen = new LinkedHashMap<>();	
		Sell tempSell = new Sell();
		
		mapSellTemp = getList();
		
		for(int k: mapSellTemp.keySet()) {
			tempSell = (Sell) mapSellTemp.get(k);
			
			if(tempSell.getVlTotal() == 0){
				mapSellOpen.put(k, tempSell);
			}
			
		}
		return mapSellOpen;
	}


	

}
