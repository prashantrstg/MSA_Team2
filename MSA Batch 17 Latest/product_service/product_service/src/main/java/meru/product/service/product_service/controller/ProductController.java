package meru.product.service.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import meru.product.service.product_service.exception.ResourceNotFoundException;
import meru.product.service.product_service.model.Category;
import meru.product.service.product_service.model.Product;
import meru.product.service.product_service.model.ProductDetails;
import meru.product.service.product_service.model.Product;
import meru.product.service.product_service.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	private ResponseEntity<Object> addProduct(@RequestBody Product product) {
		productService.SaveOrUpdate(product);
		return (ResponseEntity<Object>) ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id").buildAndExpand(product.getProductid()).toUri()).build();
	}

	@GetMapping("/products")
	public List<ProductDetails> getProductList() {
		return productService.getProductList();
	}

	@GetMapping("/product_category/{productid}")
	public Product getProductById(@PathVariable("productid") int productid)throws ResourceNotFoundException {
		Product product = productService.getProductById(productid);
		System.out.println(productid);
		if (product == null)
			throw new ResourceNotFoundException("Product Not Found");
		else
			return product;

	}

	@GetMapping("/product/{productid}")
	public ProductDetails getProductDetails(@PathVariable("productid") int productid)throws ResourceNotFoundException {
		ProductDetails product = productService.getProductDetails(productid);
		System.out.println(productid);
		if (product == null)
			throw new ResourceNotFoundException("Product Not Found");
		else
			return product;

	}

	@PutMapping("/product")
	private Product update(@RequestBody Product product) {
		productService.SaveOrUpdate(product);
		return product;
	}

	@DeleteMapping("/product/{productid}")
	private void deleteProduct(@PathVariable("productid") int productid) {
		productService.delete(productid);
	}

	@GetMapping("/products_catagories")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
}
