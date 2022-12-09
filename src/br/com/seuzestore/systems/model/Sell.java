package br.com.seuzestore.systems.model;

import java.time.LocalDateTime;


public class Sell {
	
	private int code;
	private String cpf;
	private String keyPayment;
	private PaymentEnum codPayment;
	private LocalDateTime date;
	private Double vlTotal;
	
	
	public Sell() {
		
	}
	
	public Sell(int code, String cpf, String keyPayment, PaymentEnum codPayment, LocalDateTime date, Double vlTotal) {
		super();
		this.code = code;
		this.cpf = cpf;
		this.keyPayment = keyPayment;
		this.codPayment = codPayment;
		this.date = date;
		this.vlTotal = vlTotal;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getkeyPayment() {
		return keyPayment;
	}
	
	public void setKeyPayment(String keyPayment) {
		this.keyPayment = keyPayment;
	}
	
	public PaymentEnum getPayment() {
		return codPayment;
	}
	
	
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public Double getVlTotal() {
		return vlTotal;
	}
	
	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	@Override
	public String toString() {
		return  code + ";" + cpf + ";" + keyPayment + ";" + codPayment
				+ ";" + date + ";" + vlTotal;
	}

	
	
	
}
