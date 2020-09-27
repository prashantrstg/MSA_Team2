package com.client.productview.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.client.productview.beans.ProductView;

public interface ProductViewRepository extends CrudRepository<ProductView, Long>{
	
	@Transactional

    Optional<ProductView> findByProductId(Long productId);

    void deleteByProductId(Long productId);

}
