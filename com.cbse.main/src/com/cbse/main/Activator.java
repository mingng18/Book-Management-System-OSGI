package com.cbse.main;

import java.util.Arrays;
import java.util.Date;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.cbse.analysis.consumer.AnalysisClient;
import com.cbse.book.consumer.BookClient;
import com.cbse.order.consumer.OrderClient;
import com.cbse.promo.consumer.PromoClient;
import com.cbse.promo.model.PromoCode;
import com.cbse.promo.model.PromoCriteria;
//import com.cbse.book.impl.Book;
import com.cbse.user.consumer.UserClient;

public class Activator implements BundleActivator {

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting main activator...");

		UserClient userClient = new UserClient(bundleContext);
//		userClient.signup("john.doe@example.com", "John Doe", "password123");
		userClient.login("john.doe@example.com", "password123");

		BookClient bookClient = new BookClient(bundleContext);
		PromoClient promoClient = new PromoClient(bundleContext);
		OrderClient orderClient = new OrderClient(bundleContext);
		AnalysisClient analysisClient = new AnalysisClient(bundleContext);

//		bookClient.addBook(new Book("My Book1", "Ming Ng", "Fiction", 10.50, 50, 100));

		bookClient.listBooks();

		// 1. Create a new PromoCode
//		PromoCode promoCode = new PromoCode(1, "YEAREND", "Year End Promo", 0.1, new Date(), new Date(), false,
//				Arrays.asList(new PromoCriteria(1, "Discount on all items", "Percentage", "10%"),
//						new PromoCriteria(2, "Free shipping", "Shipping", "Free")));

//		promoClient.createPromoCode(promoCode);
//
//		// 2. Add Promo Criteria to the PromoCode
//		promoClient.addPromoCriteriaByPromoId(new PromoCriteria(3, "Buy 1 get 1 free", "Special Offer", "BOGO"),
//				promoCode.getId());
//		promoClient.addPromoCriteriaByPromoId(new PromoCriteria(4, "20% off on next purchase", "Percentage", "20%"),
//				promoCode.getId());

//		promoClient.getAllActivePromoCodes();

		// 3. Update one of the criteria (let's update Criteria2)
//		PromoCriteria updatedCriteria = new PromoCriteria(2, "Free shipping on orders above $50", "Shipping",
//				"Free on orders > $50");
//		promoClient.updatePromoCriteriaByPromoId(promoCode.getId(), updatedCriteria);
//
//		// 4. Delete one of the criteria (let's delete Criteria1)
//		promoClient.deletePromoCriteriaByPromoId(promoCode.getId(), 1);
//
//		// 5. Delete the promo code
//		promoClient.deletePromoCode(promoCode.getId());
//
//		// Fetching the promo code to ensure deletion
//		promoClient.getAllActivePromoCodes();

		orderClient.initializeActiveOrder();
//		orderClient.addBookToCart(2, 3);
//		orderClient.applyPromoCode("YEAREND");
//		orderClient.checkout();

		analysisClient.displayBestSellingBooks();
		analysisClient.displayBestEarningBooks();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping main activator...");
	}

}
