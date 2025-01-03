package com.cbse.promo.api;

import com.cbse.promo.model.PromoCode;
import com.cbse.promo.model.PromoCriteria;

import java.util.List;

public interface IPromoService {

	// CRUD operations for promo codes
	void createPromoCode(PromoCode promoCode);

	void updatePromoCode(int id, PromoCode updatedPromoCode);

	void deletePromoCode(int id);

	// CRUD operations for promo criteria
	void addPromoCriteriaByPromoId(PromoCriteria promoCriteria, int promoId);

	void updatePromoCriteriaByPromoId(int promoId, PromoCriteria updatedPromoCriteria);

	void deletePromoCriteriaByPromoId(int promoId, int criteriaId);

	PromoCode getPromoCode(String code);

	List<PromoCode> getAllActivePromoCodes();
}
