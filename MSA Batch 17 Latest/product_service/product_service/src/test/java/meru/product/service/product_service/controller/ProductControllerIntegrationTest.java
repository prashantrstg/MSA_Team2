package meru.product.service.product_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;

import meru.product.service.product_service.ProductServiceApplication;
import meru.product.service.product_service.model.Category;
import meru.product.service.product_service.model.Product;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProductServiceApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test 
	public void testAllProducts()
	{
		Product[] prods= this.restTemplate
				.getForObject("http://localhost:"+ port +"/products", Product[].class);
		System.out.println(prods);
		assertEquals(0,  prods.length);
	}

	@Test
	public void testAddProduct() {

		Product product= new Product();
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:"+ port + "/addProduct", product, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testAddCategory() {
		Category category= new Category();
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:"+ port + "/addCategory", category, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}

}