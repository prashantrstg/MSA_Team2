package meru.product.service.product_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meru.product.service.product_service.model.Category;
import meru.product.service.product_service.repository.CatagoryRepository;

@Service
public class CategoryService {
	@Autowired
	CatagoryRepository categoryRepository;
	
	public void SaveOrUpdate(Category category) {
		categoryRepository.save(category);
		
	}
	public Category getCategoryById(int id) {
		Category obj = null;
		try {
			obj= categoryRepository.findById(id).get();
		}catch(Exception e) {System.out.println(e);}
		
		return obj;
	}
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}
	public void update(Category category, int categoryid) {
		categoryRepository.save(category);
	}
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(category1 ->categories.add(category1));
		return categories;
	}
}
