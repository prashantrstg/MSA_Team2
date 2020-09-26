package meru.product.service.product_service.model;

public class ProductDetails {
	int productid;
	String productname;
	String description;
	String categoryname;
	
	
	public ProductDetails(int productid, String productname, String description, String categoryname) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.description = description;
		this.categoryname = categoryname;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

}
