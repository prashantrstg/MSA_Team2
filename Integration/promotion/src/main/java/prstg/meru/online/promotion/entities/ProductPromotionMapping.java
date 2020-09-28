package prstg.meru.online.promotion.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_product")
public class ProductPromotionMapping {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "prod_id")
//	@ElementCollection
//	private List<Long> prodId=new ArrayList<Long>();
	private Long prodId;
	
//	@Column(name = "promotion_id")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id")
	private ProductPromotion productPromotion;

	public ProductPromotionMapping() {
		// TODO Auto-generated constructor stub
	}

	public ProductPromotionMapping(Long prodId, ProductPromotion productPromotion) {
		this.prodId = prodId;
		this.productPromotion = productPromotion;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public ProductPromotion getProductPromotion() {
		return productPromotion;
	}

	public void setProductPromotion(ProductPromotion productPromotion) {
		this.productPromotion = productPromotion;
	}

	

}
