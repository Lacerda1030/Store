package br.com.seuzestore.systems.services;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.seuzestore.systems.data.CartData;

import br.com.seuzestore.systems.data.StockData;

import br.com.seuzestore.systems.model.Product;



public class ProductService implements ServicesInterface {
	
	Map<Integer, Object> mapCheckProd = new LinkedHashMap<>();
	
	
	CartData cartData = new CartData();
	StockData stock = new StockData();

	Map<Integer, Object> mapProdTemp =  stock.listItems();
	
	
	public Integer getNewCod() {
		
		int cod = -1;
		
		
		//mapProdTemp = getList();
		if(!mapProdTemp.isEmpty()) {
			for(int k: mapProdTemp.keySet()) {
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
		// TODO Auto-generated method stub
		Boolean resp = true;
		
		//Product prodTemp = new Product(codigo, descricao, sku, quantidade, valor);
		Product prodTemp = (Product) obj;
		
		//VERIFICAR SE PRODUTO JA EXISTE
		if (!checkProd(prodTemp.getCode())) {
			stock.save(prodTemp);
			resp = true;
			
		}else {
			stock.updade(prodTemp);
			resp = true;
		}
		return  resp;
	}

	
	public Boolean refresh(Object obj) {
		// TODO Auto-generated method stub
		Boolean resp = true;
		
		//Product prodTemp = new Product(codigo, descricao, sku, quantidade, valor);
		Product prodTemp = (Product) obj;
		//Product pd = (Product) mapProdTemp.get(prodTemp.getCode());
		
		//prodTemp.setQuantity(prodTemp.getQuantity() + pd.getQuantity());
		stock.updade(prodTemp);
		resp = true;
		return resp;
		
		
	}
	
	
	public Boolean delete(Integer cod) {
		Boolean resp = true;
		
		stock.delete(cod);
		resp = true;
		
		return resp;
		
		
	}
	
	@Override
	public Map<Integer, Object> getList() {
		mapProdTemp = stock.listItems() ;
		return mapProdTemp;
	}
	
	
	


	public Boolean checkProd(int cod ) {
		boolean resp = false;
		
		mapCheckProd = getList();
		
		if(mapCheckProd.containsKey(cod)) {
			
				resp = true;
		}
		
		return resp;
			
	}
	
	public Boolean checkSku(String sku ) {
		boolean resp = false;
		
		mapCheckProd = getList();
		for(int k : mapCheckProd.keySet()) {
			Product prodTemp = (Product) mapCheckProd.get(k);
			if(prodTemp.getSku().equals(sku)) {
				resp = true;
			}
			
		}
		
		return resp;
			
	}


	public  boolean checkQuant(int codProd, int quantItem, int qt) {
		
		Boolean resp = true;
		
		//mapProdTemp = getList();
		Product tempProd = (Product) mapProdTemp.get(codProd);
		if(qt>0){
			if(quantItem > tempProd.getQuantity()-qt) {
				resp = false;
			}
		}else {
			if(quantItem > tempProd.getQuantity()) {
				resp = false;
			}
		}
		
		
		return resp;
	}
	
	
	
	public void renewQuant(Integer cod, Integer qt) {
		//Map<Integer, Object> mapProdTemp = new LinkedHashMap<>();
		
		int quant;
		quant = qt;
		mapProdTemp = stock.listItems();
		Product prodTemp = (Product) mapProdTemp.get(cod); 
		
		prodTemp.setQuantity(prodTemp.getQuantity() - quant);
		
		
		
	}
	
	public Boolean validateSku(String sku) {
		Boolean resp = true;
		Character letter;
		if(sku.length() != 13 || sku.isBlank()) {
			resp = false;
		}
		
		for(int i=0; i<sku.length(); i++) {
			letter = sku.charAt(i);
			if(Character.isDigit(letter)) {
				resp = false;
				break;
			}
		}
		return resp;
	}


	


	
	


	
	
	
	
	
	



	


	

	
	
 
	
	
}
