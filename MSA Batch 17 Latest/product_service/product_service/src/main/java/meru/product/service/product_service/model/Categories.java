package meru.product.service.product_service.model;

import java.util.ArrayList;
import java.util.List;


public class Categories {
	private List<Category> categoryList;
	
	public List<Category> getCategoryList() {
		if(categoryList == null) {
			categoryList = new ArrayList<Category>();
		}
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
