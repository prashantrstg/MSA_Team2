package com.client.productview.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ProductPrice 
{	

	private Long prodId;
	

	private Long price;
	

	private double tax;
	
	public ProductPrice() 
	{
	}

	public ProductPrice(Long prodId, Long price, double tax) 
	{
		super();
		this.prodId = prodId;
		this.price = price;
		this.tax = tax;
	}
	
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
}