package prstg.meru.online.promotion.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class ProductPromotion {
	@Id
	@GeneratedValue
	@Column(name = "promotion_id")
	private Long promotionId;

	@Column(name = "promotion_startdate")
	 @Temporal(TemporalType.DATE)
	@JsonDeserialize(using = DateHandler.class)
	private Date promotion_startDate;
	
	@Column(name = "promotion_end_date")
	@JsonDeserialize(using = DateHandler.class)
	 @Temporal(TemporalType.DATE)
	private Date promotion_endDate;
	@Column(name = "promotion_discount")
	private Float promotion_discount;
	@Column(name = "promotion_description")
	String promotion_description;

	public ProductPromotion() {
		// TODO Auto-generated constructor stub
	}

	public ProductPromotion(float promotion_discount, Date promotion_startDate, Date promotion_endDate,
			String promotion_description) {

		this.promotion_discount = promotion_discount;
		this.promotion_startDate = promotion_startDate;
		this.promotion_endDate = promotion_endDate;
		this.promotion_description = promotion_description;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotion_discount(Float promotion_discount) {
		this.promotion_discount = promotion_discount;
	}

	public Date getPromotion_startDate() {
		return promotion_startDate;
	}

	public void setPromotion_startDate(Date promotion_startDate) {
		this.promotion_startDate = promotion_startDate;
	}

	public Date getPromotion_endDate() {
		return promotion_endDate;
	}

	public void setPromotion_endDate(Date promotion_endDate) {
		this.promotion_endDate = promotion_endDate;
	}

	public float getPromotion_discount() {
		return promotion_discount;
	}

	public void setPromotion_discount(float promotion_discount) {
		this.promotion_discount = promotion_discount;
	}

	public String getPromotion_description() {
		return promotion_description;
	}

	public void setPromotion_description(String promotion_description) {
		this.promotion_description = promotion_description;
	}

}
