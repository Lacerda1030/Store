package br.com.seuzestore.systems.model;

public class Product {
	private Integer code;
	private String description;
	private String sku; 
	private CategoryEnum category;
	private KindEnum kind;
	private SizeEnum size;
	private DepartmentEnum department;
	private CollorEnum collor;
	private int quantity;
	private Double price;
	
	
	
	public Product() {
		
	}

		
	public Product(Integer code, String description, String sku, int quantity, Double price) {
		super();
		this.code = code;
		this.sku = sku;
		this.description = description;
		
		parseSku();
		
		/*this.categoria = categoria;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.departamento = departamento;
		this.cor = cor;*/
		this.quantity = quantity;
		this.price = price;
	}

	public void parseSku() {
		
		
		String skuCat = sku.substring(0,3);
		String skuKind = sku.substring(3,6);
		String skuDepart = sku.substring(6,9);
		String skuSize = sku.substring(9,10);
		String skuCollor =  sku.substring(10,13);
		
				
		this.category = CategoryEnum.getCategory(skuCat);
		this.kind = KindEnum.getKind(skuKind);
		this.size = SizeEnum.getSize(skuSize);
		this.department = DepartmentEnum.getDepartment(skuDepart);
		this.collor = CollorEnum.getCollor(skuCollor);
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSku() {
		return sku;
	}
	
	public CategoryEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryEnum category) {
		this.category = category;
	}
	public KindEnum getKind() {
		return kind;
	}
	public void setKind(KindEnum kind) {
		this.kind = kind;
	}
	public SizeEnum getSize() {
		return size;
	}
	public void setSize(SizeEnum size) {
		this.size = size;
	}
	public DepartmentEnum getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentEnum department) {
		this.department = department;
	}
	

	public CollorEnum getCollor() {
		return collor;
	}


	public void setCollor(CollorEnum collr) {
		this.collor = collr;
	}


	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return code + ";" + description + ";" +  sku + ";" + category + ";" + kind
				+ ";" + size + ";" + department + ";" + collor
				+ ";" + quantity + ";" + price;
	}
	
	
	
	

}
