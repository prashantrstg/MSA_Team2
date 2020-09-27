package meru.product.service.product_service.model;

import java.util.ArrayList;
import java.util.List;

public class Products {
	private List<Product> productList;
	
	public List<Product> getProductList() {
		if(productList == null) {
			productList = new ArrayList<Product>();
		}
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
