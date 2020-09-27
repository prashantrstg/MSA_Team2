package com.client.productview.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.productview.beans.ProductView;
import com.client.productview.repository.ProductViewRepository;

@Service
public class ProductViewService {

	@Autowired

    ProductViewRepository productViewRepository;



    @Transactional

    public List<ProductView> getAllProducts() {

           Iterable<ProductView> productIterable = productViewRepository.findAll();

           List<ProductView> productList = new ArrayList<>();

           productIterable.forEach(productList::add);

           return productList ;

    }



    @Transactional

    public Optional<ProductView> getProductById(Long customerKey) {

           return productViewRepository.findByProductId(customerKey);

           //return productViewRepository.findById(customerKey);

    }



    @Transactional

    public ProductView createProduct(ProductView productView) {

           return productViewRepository.save(productView);

    }



    @Transactional

    public ProductView updateProductById(ProductView product) {

           Optional<ProductView> productFetch = productViewRepository.findByProductId(product.getProductId());

          

           if(productFetch.isPresent()) {

                  ProductView productEntity = new ProductView();

                  productEntity.setProductKey(productFetch.get().getProductKey());

                  productEntity.setProductId(product.getProductId());

                  productEntity.setAvailability(product.getAvailability());

                  productEntity.setSellingPrice(product.getSellingPrice());

                  return productViewRepository.save(productEntity);

           }

           return null;

    }



    @Transactional

    public boolean deleteProductById(Long productId) {

           Optional<ProductView> customer = productViewRepository.findByProductId(productId);

           if(customer.isPresent()) {

                  productViewRepository.deleteByProductId(productId);

                  return true;

           }

           return false;

    }
}
