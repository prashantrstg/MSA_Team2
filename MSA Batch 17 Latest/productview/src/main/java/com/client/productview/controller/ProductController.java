package com.client.productview.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.client.productview.beans.ProductDetails;
import com.client.productview.beans.ProductDetailsBean;
import com.client.productview.beans.ProductPrice;
import com.client.productview.beans.ProductView;
import com.client.productview.exceptions.ProductFoundExceptionException;
import com.client.productview.service.ProductViewService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController

@RefreshScope

@RequestMapping(path = "/ProductViewService")

public class ProductController {

	private static String PRODUCT_NOT_FOUND = "Product not found";

	@Autowired

	ProductViewService productViewService;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Value("${productview.priceService.uri}")

	String priceServiceURI;
	
	@Value("productview.productCategory.uri}")
	String priceCategoryURI;

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

	@GetMapping("/productMasterDetails/{productId}")
	@HystrixCommand(fallbackMethod = "getMasterProductDefaultDetails", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public ResponseEntity<ProductDetailsBean> getMasterProductDetails(@PathVariable("productId") Long productId) {

		Optional<ProductView> productDetails = productViewService.getProductById(productId);
		ProductDetailsBean details = new ProductDetailsBean();
		ServiceInstance serviceInstance = loadBalancer.choose("mas-pricing-service");
		System.out.println(serviceInstance.getUri() + priceServiceURI + productId);
		String baseUrl = serviceInstance.getUri().toString() + priceServiceURI + productId;
		if (!productDetails.isPresent()) {
			throw new ProductFoundExceptionException(PRODUCT_NOT_FOUND);
		} else {
			details.setProductView(productDetails);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ProductPrice> response = null;
			try {
				response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), ProductPrice.class);
				details.setProductPrice(response.getBody());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return ResponseEntity.ok(details);
	}

	public ResponseEntity<ProductDetailsBean> getMasterProductDefaultDetails(@PathVariable("productId") Long productId) {
		Optional<ProductView> productDetails = productViewService.getProductById(productId);
		ProductDetailsBean details = new ProductDetailsBean();
		ProductPrice productPrice = new ProductPrice();
		details.setProductPrice(productPrice);
		details.setProductView(productDetails);
		return ResponseEntity.ok(details);
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	
	@GetMapping("/productMasterCategoryDetails/{productId}")
	@HystrixCommand(fallbackMethod = "getMasterProductDefaultDetails", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public ResponseEntity<ProductDetailsBean> productMasterCategoryDetails(@PathVariable("productId") Long productId) {

		Optional<ProductView> productDetails = productViewService.getProductById(productId);
		ProductDetailsBean details = new ProductDetailsBean();
		if (!productDetails.isPresent()) {
			throw new ProductFoundExceptionException(PRODUCT_NOT_FOUND);
		} else {
			details.setProductView(productDetails);
			ServiceInstance serviceInstance = loadBalancer.choose("mas-product-service");
			System.out.println(serviceInstance.getUri() + priceCategoryURI + productId);
			String baseUrl = serviceInstance.getUri().toString() + priceCategoryURI + productId;
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ProductDetails> response = null;
			try {
				response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), ProductDetails.class);
				details.setProductDetails(response.getBody());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return ResponseEntity.ok(details);
	}

}
