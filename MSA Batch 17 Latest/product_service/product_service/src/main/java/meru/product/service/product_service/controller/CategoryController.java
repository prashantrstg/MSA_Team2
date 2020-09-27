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
import meru.product.service.product_service.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/addCategory")
	private ResponseEntity<Object> addCategory(@RequestBody Category category) {
		categoryService.SaveOrUpdate(category);
		return (ResponseEntity<Object>) ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id").buildAndExpand(category.getCategoryid()).toUri()).build();
	}

	@GetMapping("/category/{categoryid}")
	public Category getCategoryById(@PathVariable("categoryid") int categoryid) throws ResourceNotFoundException {
		Category category = categoryService.getCategoryById(categoryid);
		System.out.println(categoryid);
		if (category == null)
			throw new ResourceNotFoundException("Category Not Found");
		else
			return category;

	}

	@PutMapping("/category")
	private Category update(@RequestBody Category category) {
		categoryService.SaveOrUpdate(category);
		return category;
	}

	@DeleteMapping("/category/{categoryid}")
	private void deleteCategory(@PathVariable("categoryid") int categoryid) {
		categoryService.delete(categoryid);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
}
