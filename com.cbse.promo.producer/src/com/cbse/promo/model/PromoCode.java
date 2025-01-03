package com.cbse.promo.model;

import java.util.Date;
import java.util.List;

public class PromoCode {
	private int id;
	private String promoCode;
	private String name;
	private double discountRate; // Discount rate in percentage (e.g., 20 for 20%)
	private Date effectiveStartTimestamp;
	private Date effectiveEndTimestamp;
	private boolean isDeleted;
	private List<PromoCriteria> promoCriteria; // Relationship with promo criteria

	public PromoCode(int id, String promoCode, String name, double discountRate, Date effectiveStartTimestamp,
			Date effectiveEndTimestamp, boolean isDeleted, List<PromoCriteria> promoCriteria) {
		this.id = id;
		this.promoCode = promoCode;
		this.name = name;
		this.discountRate = discountRate;
		this.effectiveStartTimestamp = effectiveStartTimestamp;
		this.effectiveEndTimestamp = effectiveEndTimestamp;
		this.isDeleted = isDeleted;
		this.promoCriteria = promoCriteria;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public Date getEffectiveStartTimestamp() {
		return effectiveStartTimestamp;
	}

	public void setEffectiveStartTimestamp(Date effectiveStartTimestamp) {
		this.effectiveStartTimestamp = effectiveStartTimestamp;
	}

	public Date getEffectiveEndTimestamp() {
		return effectiveEndTimestamp;
	}

	public void setEffectiveEndTimestamp(Date effectiveEndTimestamp) {
		this.effectiveEndTimestamp = effectiveEndTimestamp;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public List<PromoCriteria> getPromoCriteria() {
		return promoCriteria;
	}

	public void setPromoCriteria(List<PromoCriteria> promoCriteria) {
		this.promoCriteria = promoCriteria;
	}

	@Override
	public String toString() {
		return "PromoCode{" + "id=" + id + ", promoCode='" + promoCode + '\'' + ", name='" + name + '\''
				+ ", discountRate=" + discountRate + ", effectiveStartTimestamp=" + effectiveStartTimestamp
				+ ", effectiveEndTimestamp=" + effectiveEndTimestamp + ", isDeleted=" + isDeleted + ", promoCriteria="
				+ promoCriteria + '}';
	}
}
