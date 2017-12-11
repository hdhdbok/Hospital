package edu.ncwu.data;

import java.math.BigDecimal;

public class Medicine {
	private int drug_sno;
	private String drug_name;
	private int number;
	private BigDecimal price;
	private String drug_place;
	
	public Medicine() {
		super();
	}
	public Medicine(String drug_name, int number, BigDecimal price, String drug_place) {
		super();
		this.drug_name = drug_name;
		this.number = number;
		this.price = price;
		this.drug_place = drug_place;
	}
	public Medicine(int drug_sno, String drug_name, int number, BigDecimal price, String drug_place) {
		super();
		this.drug_sno = drug_sno;
		this.drug_name = drug_name;
		this.number = number;
		this.price = price;
		this.drug_place = drug_place;
	}
	public int getDrug_sno() {
		return drug_sno;
	}
	public void setDrug_sno(int drug_sno) {
		this.drug_sno = drug_sno;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDrug_place() {
		return drug_place;
	}
	public void setDrug_place(String drug_place) {
		this.drug_place = drug_place;
	}
	
	
}
