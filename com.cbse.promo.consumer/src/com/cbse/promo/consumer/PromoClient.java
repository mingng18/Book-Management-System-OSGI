package com.cbse.promo.consumer;

import com.cbse.promo.api.IPromoService;
import com.cbse.promo.model.PromoCode;
import com.cbse.promo.model.PromoCriteria;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class PromoClient {
	private IPromoService promoService;

	// Constructor to initialize the service via OSGi context
	public PromoClient(BundleContext context) {
		// Retrieve the IPromoService from OSGi service registry
		ServiceReference<IPromoService> serviceRef = context.getServiceReference(IPromoService.class);
		if (serviceRef != null) {
			promoService = context.getService(serviceRef);
			System.out.println("PromoService retrieved successfully.");
		} else {
			System.out.println("PromoService not available.");
		}
	}

	// Method to create a promo code
	public void createPromoCode(PromoCode promoCode) {
		if (promoService != null) {
			try {
				promoService.createPromoCode(promoCode);
				System.out.println("Promo code created successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to create promo codes.");
		}
	}

	// Method to update a promo code
	public void updatePromoCode(int id, PromoCode updatedPromoCode) {
		if (promoService != null) {
			try {
				promoService.updatePromoCode(id, updatedPromoCode);
				System.out.println("Promo code updated successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to update promo codes.");
		}
	}

	// Method to delete a promo code
	public void deletePromoCode(int id) {
		if (promoService != null) {
			try {
				promoService.deletePromoCode(id);
				System.out.println("Promo code deleted successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to delete promo codes.");
		}
	}

	// Method to add promo criteria to a promo code
	public void addPromoCriteriaByPromoId(PromoCriteria promoCriteria, int promoId) {
		if (promoService != null) {
			try {
				promoService.addPromoCriteriaByPromoId(promoCriteria, promoId);
				System.out.println("Promo criteria added successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to add promo criteria.");
		}
	}

	// Method to update promo criteria for a promo code
	public void updatePromoCriteriaByPromoId(int promoId, PromoCriteria updatedPromoCriteria) {
		if (promoService != null) {
			try {
				promoService.updatePromoCriteriaByPromoId(promoId, updatedPromoCriteria);
				System.out.println("Promo criteria updated successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to update promo criteria.");
		}
	}

	// Method to delete promo criteria for a promo code
	public void deletePromoCriteriaByPromoId(int promoId, int criteriaId) {
		if (promoService != null) {
			try {
				promoService.deletePromoCriteriaByPromoId(promoId, criteriaId);
				System.out.println("Promo criteria deleted successfully!");
			} catch (SecurityException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to delete promo criteria.");
		}
	}

	// Method to fetch a promo code by its code
	public void getPromoCode(String code) {
		if (promoService != null) {
			PromoCode promoCode = promoService.getPromoCode(code);
			if (promoCode != null) {
				System.out.println("Promo Code fetched: " + promoCode.getPromoCode() + " - " + promoCode.getName());
			} else {
				System.out.println("Promo code not found.");
			}
		} else {
			System.out.println("No service available to fetch promo codes.");
		}
	}

	// Method to get all active promo codes
	public void getAllActivePromoCodes() {
		if (promoService != null) {
			try {
				System.out.println("Fetching all active promo codes...");
				promoService.getAllActivePromoCodes().forEach(promoCode -> {
					System.out.println("Promo Code: " + promoCode.getPromoCode() + " - " + promoCode.getName() + " - "
							+ promoCode.getPromoCriteria().getFirst());
				});
			} catch (Exception e) {
				System.out.println("Error fetching promo codes: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to fetch active promo codes.");
		}
	}
}
