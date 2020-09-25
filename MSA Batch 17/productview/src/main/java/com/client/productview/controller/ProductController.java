package com.client.productview.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.productview.beans.ProductView;
import com.client.productview.exceptions.ProductFoundExceptionException;
import com.client.productview.service.ProductViewService;


@RestController

@RefreshScope

@RequestMapping(path = "/ProductViewService")

public class ProductController {

	private static String PRODUCT_NOT_FOUND = "Product not found";

	@Autowired

	ProductViewService productViewService;

	@Value("${productview.message}")

	String productViewMessage;

	@RequestMapping(value = "/msg")

	public String configMessageText() {

		return productViewMessage;

	}

	@GetMapping("/products")

	public ResponseEntity<List<ProductView>> getAllCustomers() {

		return ResponseEntity.ok(productViewService.getAllProducts());

	}

	@GetMapping("/productdetails/{productId}")

	public ResponseEntity<ProductView> getProductDetails(@PathVariable("productId") Long productId) {

		Optional<ProductView> productDetails = productViewService.getProductById(productId);

		if (!productDetails.isPresent()) {

			throw new ProductFoundExceptionException(PRODUCT_NOT_FOUND);

		}

		return ResponseEntity.ok(productDetails.get());

	}

	@PostMapping("/product")

	public ResponseEntity<ProductView> createCustomer(@RequestBody ProductView productDetails) {

		return ResponseEntity.ok(productViewService.createProduct(productDetails));

	}

	@PutMapping("/product/{id}")

	public ResponseEntity<ProductView> updateCustomerById(@PathVariable("id") Long productId,
			@RequestBody ProductView product) {

		product.setProductId(productId);

		product = productViewService.updateProductById(product);

		if (product == null) {

			throw new ProductFoundExceptionException(PRODUCT_NOT_FOUND);

		}

		return ResponseEntity.ok(product);

	}

	@DeleteMapping("/product/{id}")

	public ResponseEntity<ProductView> deleteProductById(@PathVariable("id") Long productId) {

		if (!productViewService.deleteProductById(productId)) {

			throw new ProductFoundExceptionException(PRODUCT_NOT_FOUND);

		}

		return ResponseEntity.ok().build();

	}

}
