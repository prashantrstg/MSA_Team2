package prstg.meru.online.promotion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prstg.meru.online.promotion.entities.ProductPromotion;
import prstg.meru.online.promotion.exception.ResourceNotFoundException;
import prstg.meru.online.promotion.repository.ProductPromotionRepository;

@RestController
public class PromotionController {
	@Autowired
	private ProductPromotionRepository productPromotionRepository;

	@GetMapping("/meru/product/promotion/{promotionId}")
	public ProductPromotion getProductPromotion(@PathVariable long promotionId) {
		Optional<ProductPromotion> promotion = productPromotionRepository.findById(promotionId);
		if (!promotion.isPresent()) {
			throw new ResourceNotFoundException("Promotion id-" + promotionId);
		}
		return promotion.get();
	}
	@GetMapping("/meru/product/promotions")
	public List<ProductPromotion> getAllPromotions(){
		List<ProductPromotion> promotions=new ArrayList<ProductPromotion>();
		if(productPromotionRepository.findAll()==null)
			return promotions;
		for(ProductPromotion promotion:productPromotionRepository.findAll())
			promotions.add(promotion);
		return promotions;
	}

	@PostMapping("/meru/product/promotion")
	public ProductPromotion addProductPromotion(@Valid @RequestBody ProductPromotion productPromotion) {
		ProductPromotion promotion = productPromotionRepository.save(productPromotion);
		return promotion;
	}

	@PutMapping("/meru/product/promotion/{promotionId}")
	public ProductPromotion updateProductPromotion(@RequestBody ProductPromotion productPromotion) {
		Optional<ProductPromotion> promotion = productPromotionRepository.findById(productPromotion.getPromotionId());
		if (!promotion.isPresent()) {
			throw new ResourceNotFoundException("Promotion id-" + promotion.get().getPromotionId());
		}

		ProductPromotion promo = promotion.get();
		promo.setPromotion_discount(productPromotion.getPromotion_discount());
		promo.setPromotion_startDate(productPromotion.getPromotion_startDate());
		promo.setPromotion_endDate(productPromotion.getPromotion_endDate());
		promo.setPromotion_description(productPromotion.getPromotion_description());

		return productPromotionRepository.save(promo);
	}
	@DeleteMapping("/meru/product/promotion/delete/{promotionId}")
	public void removeProductPromotion(@PathVariable long promotionId) {
		Optional<ProductPromotion> promotion = productPromotionRepository.findById(promotionId);
		if (!promotion.isPresent()) {
			throw new ResourceNotFoundException("Promotion id-" + promotionId);
		}
		 productPromotionRepository.deleteById(promotionId);
	}
	
	
}
