package com.client.productview.beans;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailsBean {

	ProductPrice productPrice;
	Optional<com.client.productview.beans.ProductView> ProductView;
	ProductDetails productDetails;

	ProductPromotion[] promotionList;

	public ProductPromotion[] getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(ProductPromotion[] productPromotions) {
		this.promotionList = productPromotions;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice2) {
		this.productPrice = productPrice2;
	}

	public Optional<com.client.productview.beans.ProductView> getProductView() {
		return ProductView;
	}

	public void setProductView(Optional<com.client.productview.beans.ProductView> productDetails) {
		ProductView = productDetails;
	}

}
