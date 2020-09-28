package prstg.meru.online.promotion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prstg.meru.online.promotion.entities.ProductPromotion;
import prstg.meru.online.promotion.entities.ProductPromotionMapping;
import prstg.meru.online.promotion.exception.ResourceNotFoundException;
import prstg.meru.online.promotion.repository.ProductPromotionMappingRepository;
import prstg.meru.online.promotion.repository.ProductPromotionRepository;

@RestController
public class ProductPromotionMappingController {
	@Autowired
	private ProductPromotionMappingRepository mappingRepository;
	@Autowired
	private ProductPromotionRepository productPromotionRepository;

	@GetMapping("/meru/product/promotionmapping/{prodId}")
	public List<Long> getPromotion(@PathVariable Long prodId) {
		List<ProductPromotionMapping> mappings = mappingRepository.findByProdId(prodId);

		if (mappings == null) {
			throw new ResourceNotFoundException("ProductId-" + prodId);
		}

		List<Long> promIds = new ArrayList<Long>();
		for (ProductPromotionMapping pid : mappings)
			promIds.add(pid.getProductPromotion().getPromotionId());

//	List<ProductPromotionMapping> mappings=	(List<ProductPromotionMapping>) mappingRepository.findAllByPromotionId(promotionId);
//			List<Long> products = promotionMapping.get().getProdId();
		return promIds;
	}

	/*
	 * @GetMapping("/meru/product/promotionmapping/{promotionId}") public List<Long>
	 * getProducts(@PathVariable Long promotionId) { ProductPromotion p =
	 * productPromotionRepository.findById(promotionId).get();
	 * System.out.println(p); List<ProductPromotionMapping> mappingList =
	 * mappingRepository.findAllByproductPromotion(p); if (mappingList == null) {
	 * throw new ResourceNotFoundException("PromotionId-" + promotionId); }
	 * List<Long> mappings = new ArrayList<>();
	 * mappingRepository.findAllByproductPromotion(p).forEach(d -> {
	 * mappings.add(d.getProdId()); });
	 * 
	 * // List<ProductPromotionMapping> mappings= (List<ProductPromotionMapping>)
	 * mappingRepository.findAllByPromotionId(promotionId); // List<Long> products =
	 * promotionMapping.get().getProdId(); return mappings; }
	 */

	@GetMapping("/meru/product/promotionmappings")
	public List<ProductPromotionMapping> getAllMappings() {
		List<ProductPromotionMapping> mappings = new ArrayList<ProductPromotionMapping>();
		if (mappingRepository.findAll() == null)
			return mappings;
		for (ProductPromotionMapping mapping : mappingRepository.findAll()) {
			mappings.add(mapping);
		}
		return mappings;
	}

	@PostMapping("/meru/product/promotionmapping")
	public List<ProductPromotionMapping> applyPromotion(@RequestBody List<ProductPromotionMapping> mapping) {
		Long promtionId;
		List<ProductPromotionMapping> updated = new ArrayList<ProductPromotionMapping>();

		for (int i = 0; i < mapping.size(); i++) {
			promtionId = mapping.get(i).getProductPromotion().getPromotionId();
			ProductPromotion p = productPromotionRepository.findById(promtionId).get();
				
			updated.add(new ProductPromotionMapping(mapping.get(i).getProdId(), p));
			
		}
		return (List<ProductPromotionMapping>) mappingRepository.saveAll(updated);

	}

	@PutMapping("/meru/product/promotionmapping")
	public List<ProductPromotionMapping> updateProductPromotion(@RequestBody ProductPromotionMapping mapping) {
		ProductPromotion p = productPromotionRepository.findById(mapping.getProductPromotion().getPromotionId()).get();
		if (p == null)
			throw new ResourceNotFoundException("PromotionId-" + p.getPromotionId());
		System.out.println(p);
		List<ProductPromotionMapping> mappingList = mappingRepository.findAllByproductPromotion(p);
		if (mappingList == null) {

			return (List<ProductPromotionMapping>) mappingRepository.saveAll(mappingList);

		} else {
			List<ProductPromotionMapping> updated = new ArrayList<>();
			mappingList.forEach(d -> {
				updated.add(new ProductPromotionMapping(mapping.getProdId(), p));
			});

			return (List<ProductPromotionMapping>) mappingRepository.saveAll(updated);
		}
	}

	@DeleteMapping("/meru/product/promotionmapping/{promotionId}")
	public void removeProductPromotionMapping(@PathVariable Long promotionId) {

		ProductPromotion p = productPromotionRepository.findById(promotionId).get();
		System.out.println(p);
		List<ProductPromotionMapping> mappingList = mappingRepository.findAllByproductPromotion(p);
		if (mappingList == null) {
			throw new ResourceNotFoundException("PromotionId-" + promotionId);
		}

		mappingRepository.deleteAll(mappingList);

	}

}
