package com.client.productview.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_VIEW")
@SequenceGenerator(name = "productsequence", sequenceName = "productsequence", initialValue=1000,allocationSize=1)
public class ProductView {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="productsequence")
	@Column(name="PRODUCTKEY")
	private Long productKey;
	
	@Column(name="PRODUCTID")
	private Long productId;
	
	@Column(name="SELLINGPRICE")
	private Long sellingPrice;
	
	public Long getProductKey() {
		return productKey;
	}

	public void setProductKey(Long productKey) {
		this.productKey = productKey;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Column(name="AVAILABILITY")
	private String availability;

}

