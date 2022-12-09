package br.com.seuzestore.systems.model;

public class Cart {
	
	private Integer codItem, codSell, codProd;
	private String description;
	private int quantity;
	private Double vlItem;
	
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Cart(Integer codItem, Integer codSell, Integer codProd, String description, int quantity,
			Double vlItem) {
		super();
		this.codItem = codItem;
		this.codSell = codSell;
		this.codProd = codProd;
		this.description = description;
		this.quantity = quantity;
		this.vlItem = vlItem;
	}
	
	
	public Integer getCodItem() {
		return codItem;
	}
	public void setCodItem(Integer codItem) {
		this.codItem = codItem;
	}
	public Integer getCodSell() {
		return codSell;
	}
	public void setCodSell(Integer codSell) {
		this.codSell = codSell;
	}
	public Integer getCodProd() {
		return codProd;
	}
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescricaoo(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}


	@Override
	public String toString() {
		return   codItem + ";" + codSell +  ";"  + codProd + ";" + description + ";" + quantity + ";" + vlItem;
	
	}
	
	
	
	
	
	
	
	

}
