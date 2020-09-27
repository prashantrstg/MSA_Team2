package meru.product.service.product_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meru.product.service.product_service.model.Category;
import meru.product.service.product_service.model.Product;
import meru.product.service.product_service.model.ProductDetails;
import meru.product.service.product_service.repository.CatagoryRepository;
import meru.product.service.product_service.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CatagoryRepository categoryRepository;
	public void SaveOrUpdate(Product product) {
		Optional<Category> c= categoryRepository.findById(product.getCategory().getCategoryid());
		product.setCategory(c.get());
		productRepository.save(product);
	}
	public Product getProductById(int id) {
		Product obj = null;
		obj= productRepository.findById(id).get();
		return obj;
	}
	public ProductDetails getProductDetails(int id) {
		Product obj = null;
		obj= productRepository.findById(id).get();
		return (ProductDetails) productRepository.getProductDetails(id);
	}
	public void delete(int id) {
		productRepository.deleteById(id);
	}
	public void update(Product product, int productid) {
		productRepository.save(product);
	}
	public List<Product> getAllProducts(){
		List<Product> categories = new ArrayList<>();
		productRepository.findAll().forEach(product1 ->categories.add(product1));
		return categories;
	}
	public List<ProductDetails> getProductList(){
		return productRepository.getProductList();
	}
}
