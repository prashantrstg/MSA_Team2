package com.client.productview.beans;

public class ProductPromotionMapping {

	private Long id;
	private Long prodId;
	private ProductPromotion productPromotion;

	public ProductPromotionMapping() {
		// TODO Auto-generated constructor stub
	}

	public ProductPromotionMapping(Long prodId, ProductPromotion productPromotion) {
		this.prodId = prodId;
		this.productPromotion = productPromotion;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public ProductPromotion getProductPromotion() {
		return productPromotion;
	}

	public void setProductPromotion(ProductPromotion productPromotion) {
		this.productPromotion = productPromotion;
	}

}
