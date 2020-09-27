package meru.product.service.product_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import meru.product.service.product_service.model.Product;
import meru.product.service.product_service.model.ProductDetails;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT new meru.product.service.product_service.model.ProductDetails(p.productid, p.productname, p.description, c.categoryname) from Product p JOIN p.category c")
	public List<ProductDetails> getProductList();
	
	@Query("SELECT new meru.product.service.product_service.model.ProductDetails(p.productid, p.productname, p.description, c.categoryname) from Product p JOIN p.category c WHERE p.productid = :productid")
	public ProductDetails getProductDetails(@Param("productid") int id);		

}
