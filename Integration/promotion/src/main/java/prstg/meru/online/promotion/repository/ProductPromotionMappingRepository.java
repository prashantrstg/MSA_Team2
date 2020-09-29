package prstg.meru.online.promotion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import prstg.meru.online.promotion.entities.ProductPromotion;
import prstg.meru.online.promotion.entities.ProductPromotionMapping;

public interface ProductPromotionMappingRepository extends CrudRepository<ProductPromotionMapping,Long> {

	//void findAllBypromotionId();

//	Iterable<ProductPromotionMapping>  findByPromotionId(Long promotionId);
List<ProductPromotionMapping> findAllByproductPromotion(ProductPromotion p );

	List<ProductPromotionMapping> findByProdId(Long prodId);

}
