package br.com.seuzestore.systems.data;

import java.util.LinkedHashMap;

import java.util.Map;

import br.com.seuzestore.systems.model.Product;


public class StockData implements DataInterface{
	static Map<Integer, Object> mapStock = new LinkedHashMap<>();

	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		Product prodRecebido = (Product) obj;
		//mapStock.put(prodRecebido.getCodigo(), prodRecebido.getDescricao() + ";" +   prodRecebido.getCategoria() + ";" +  prodRecebido.getTipo() + ";" +  prodRecebido.getDepartamento() + ";" + prodRecebido.getTamanho() +  ";" + prodRecebido.getQuantidade() + ";" + String.valueOf(prodRecebido.getValor()));
		mapStock.put(prodRecebido.getCode(), prodRecebido);
	}

	
	@Override
	public void updade(Object obj) {
		// TODO Auto-generated method stub
		Product prodRecebido = (Product) obj;
		
		
		/*Product tempProd = (Product) mapStock.get(prodRecebido.getCodigo());  
		int quant = tempProd.getQuantidade() + prodRecebido.getQuantidade();
		prodRecebido.setQuantidade(quant);*/
		
		
		mapStock.replace(prodRecebido.getCode(), prodRecebido);
		
	}

	

	@Override
	public void delete(Object obj) {
				
		mapStock.remove(obj);
		
		
	}

	
	@Override
	public Map<Integer, Object> listItems() {
		// TODO Auto-generated method stub
		return mapStock;
	}

	

	
	
}
