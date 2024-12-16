package com.cbse.order.api;

import java.util.List;

import com.cbse.order.model.Order;

public interface IOrderService {

	// Add a book to the cart (auto-create an order if not present)
	void addBookToCart(int bookId, int quantity);

	// Apply a promo code before checkout
	void applyPromoCode(String promoCode);

	// Checkout the order (update status and display total)
	void createOrder();

	// Cancel the order before checkout
	void cancelOrder(int orderId);

	// Update the order status (e.g., Admin functionality)
	void updateOrderStatus(int orderId, String newStatus, boolean isAdmin);

	// Check the current order status
	void checkOrderStatus(int orderId);

	// Get all orders for current user in any status
	List<Order> getAllOrdersForUser();

	// Get all orders for current user in unpaid status
	void initializeActiveOrder();

}