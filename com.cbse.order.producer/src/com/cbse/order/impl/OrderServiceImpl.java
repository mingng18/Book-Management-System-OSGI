package com.cbse.order.impl;

import com.cbse.order.repo.OrderRepository;
import com.cbse.promo.api.IPromoService;
import com.cbse.promo.model.PromoCode;
import com.cbse.order.api.IOrderService;
import com.cbse.order.model.Order;

import java.util.List;

public class OrderServiceImpl implements IOrderService {

	private final OrderRepository orderRepository;
	private final IPromoService promoService;
	private int currentOrderId = -1; // Active unpaid order ID
	private int currentUserId;
	private double promoDiscountRate = 0.0; // Tracks promo discount rate

	// Constructor Injection
	public OrderServiceImpl(int userId, IPromoService promoService) {
		this.orderRepository = new OrderRepository();
		this.promoService = promoService;
		this.currentUserId = userId;
	}

	@Override
	public void addBookToCart(int bookId, int quantity) {
		if (currentOrderId == -1) {
			currentOrderId = orderRepository.createOrder(currentUserId, 0.0);
			System.out.println("New order created with ID: " + currentOrderId);
		}
		orderRepository.addBookToCart(bookId, currentOrderId, quantity);
		System.out.println("Book (ID: " + bookId + ") added to cart with quantity: " + quantity);
	}

	@Override
	public void applyPromoCode(String promoCode) {
		if (currentOrderId == -1) {
			System.out.println("No active order to apply promo code. Please add a book to the cart first.");
			return;
		}

		// Fetch discount rate from the database
		PromoCode currentPromoCode = promoService.getPromoCode(promoCode);

		if (currentPromoCode == null) {
			System.out.println("Invalid promo code or promo code has expired.");
			return;
		}

		// Apply promo code
		orderRepository.applyPromoCode(currentOrderId, currentPromoCode.getId());
		promoDiscountRate = currentPromoCode.getDiscountRate();
		System.out.println("Promo code applied: " + promoCode + " with discount rate: " + promoDiscountRate + "%");
	}

	@Override
	public void createOrder() {
		if (currentOrderId == -1) {
			System.out.println("No active order to checkout. Please add a book to the cart first.");
			return;
		}

		double totalPrice = orderRepository.calculateTotalPrice(currentOrderId);

		if (promoDiscountRate > 0.0) {
			totalPrice -= (totalPrice * (promoDiscountRate / 100));
			System.out.println("Discount of " + promoDiscountRate + "% applied.");
		}

		orderRepository.updateOrderStatus(currentOrderId, "CHECKED_OUT");
		System.out.println("Order (ID: " + currentOrderId + ") checked out successfully.");
		System.out.println("Final Total Price: $" + totalPrice);

		resetOrder();
	}

	@Override
	public void cancelOrder(int orderId) {
		String currentStatus = orderRepository.getOrderStatus(orderId);

		if ("CHECKED_OUT".equalsIgnoreCase(currentStatus)) {
			System.out.println("Cannot cancel order (ID: " + orderId + "). It has already been checked out.");
		} else {
			orderRepository.cancelOrder(orderId);
			System.out.println("Order (ID: " + orderId + ") cancelled successfully.");
			resetOrder();
		}
	}

	@Override
	public void updateOrderStatus(int orderId, String newStatus, boolean isAdmin) {
		if (!isAdmin) {
			System.out.println("Unauthorized: Only admins can update order status.");
			return;
		}
		orderRepository.updateOrderStatus(orderId, newStatus);
		System.out.println("Order (ID: " + orderId + ") status updated to: " + newStatus);
	}

	@Override
	public void checkOrderStatus(int orderId) {
		String status = orderRepository.getOrderStatus(orderId);
		System.out.println("Order (ID: " + orderId + ") status: " + status);
	}

	@Override
	public List<Order> getAllOrdersForUser() {
		List<Order> orders = orderRepository.getAllOrdersForUser(currentUserId);
		System.out.println("Retrieved " + orders.size() + " orders for user ID: " + currentUserId);
		return orders;
	}

	@Override
	public void initializeActiveOrder() {
		Order activeOrder = orderRepository.getUnpaidOrder(currentUserId);
		if (activeOrder != null) {
			this.currentOrderId = activeOrder.getId();
			System.out.println("Active unpaid order retrieved: " + currentOrderId);
		} else {
			System.out.println("No active unpaid orders found. Ready to create a new one.");
		}
	}

	private void resetOrder() {
		currentOrderId = -1;
		promoDiscountRate = 0.0;
	}
}
