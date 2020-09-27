package meru.product.service.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import meru.product.service.product_service.model.Category;

public interface CatagoryRepository extends JpaRepository<Category, Integer>{

}
