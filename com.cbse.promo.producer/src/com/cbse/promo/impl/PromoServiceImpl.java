package com.cbse.promo.impl;

import com.cbse.promo.api.IPromoService;
import com.cbse.promo.model.PromoCode;
import com.cbse.promo.model.PromoCriteria;
import com.cbse.promo.repo.PromoRepository;
import com.cbse.user.api.IUserService;

import java.util.List;

public class PromoServiceImpl implements IPromoService {

	private PromoRepository promoCodeRepository;
	private IUserService userService;

	// Constructor injection for the repository layer and user service for role
	// checking
	public PromoServiceImpl(IUserService userService2) {
		this.promoCodeRepository = new PromoRepository(); // You can replace with DI
		this.userService = userService2; // Replace with DI if necessary
	}

	// Helper method to check if the current user is an admin (retrieve user info
	// from session)
	private boolean isAdmin() {
		return userService.userIsAdmin();
	}

	@Override
	public void createPromoCode(PromoCode promoCode) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can create promo codes.");
		}
		promoCodeRepository.createPromoCode(promoCode);
	}

	@Override
	public void updatePromoCode(int id, PromoCode updatedPromoCode) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can update promo codes.");
		}
		promoCodeRepository.updatePromoCode(id, updatedPromoCode);
	}

	@Override
	public void deletePromoCode(int id) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can delete promo codes.");
		}
		promoCodeRepository.deletePromoCode(id);
	}

	@Override
	public void addPromoCriteriaByPromoId(PromoCriteria promoCriteria, int promoId) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can add promo criteria.");
		}
		promoCodeRepository.addPromoCriteriaByPromoId(promoCriteria, promoId);
	}

	@Override
	public void updatePromoCriteriaByPromoId(int promoId, PromoCriteria updatedPromoCriteria) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can update promo criteria.");
		}
		promoCodeRepository.updatePromoCriteriaByPromoId(promoId, updatedPromoCriteria);
	}

	@Override
	public void deletePromoCriteriaByPromoId(int promoId, int criteriaId) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can delete promo criteria.");
		}
		promoCodeRepository.deletePromoCriteriaByPromoId(promoId, criteriaId);
	}

	@Override
	public PromoCode getPromoCode(String code) {
		// Retrieve the promo code from the repository
		PromoCode promoCode = promoCodeRepository.getPromoCode(code);

		if (promoCode != null) {
			// Retrieve the promo criteria for this promo code
			List<PromoCriteria> promoCriteriaList = promoCodeRepository.getAllPromoCriteriaByPromoId(promoCode.getId());
			promoCode.setPromoCriteria(promoCriteriaList); // Assuming PromoCode has a setter for promoCriteria
		}
		
		System.out.println(promoCode);

		return promoCode;
	}

	@Override
	public List<PromoCode> getAllActivePromoCodes() {
		// Retrieve all active promo codes
		List<PromoCode> promoCodes = promoCodeRepository.getAllActivePromoCodes();

		// For each promo code, retrieve its associated promo criteria
		for (PromoCode promoCode : promoCodes) {
			List<PromoCriteria> promoCriteriaList = promoCodeRepository.getAllPromoCriteriaByPromoId(promoCode.getId());
			promoCode.setPromoCriteria(promoCriteriaList); // Assuming PromoCode has a setter for promoCriteria
		}

		System.out.println(promoCodes);
		
		return promoCodes;
	}
}
