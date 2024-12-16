package com.cbse.order.consumer;

import com.cbse.order.api.IOrderService;
import com.cbse.order.model.Order;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.List;

public class OrderClient {
	private IOrderService orderService;

	// Constructor to initialize the service via OSGi context
	public OrderClient(BundleContext context) {
		// Retrieve the IOrderService from OSGi service registry
		ServiceReference<IOrderService> serviceRef = context.getServiceReference(IOrderService.class);
		if (serviceRef != null) {
			orderService = context.getService(serviceRef);
			System.out.println("OrderService retrieved successfully.");
		} else {
			System.out.println("OrderService not available.");
		}
	}

	// Method to add a book to the cart
	public void addBookToCart(int bookId, int quantity) {
		if (orderService != null) {
			try {
				orderService.addBookToCart(bookId, quantity);
				System.out.println("Book added to cart successfully: BookID = " + bookId + ", Quantity = " + quantity);
			} catch (Exception e) {
				System.out.println("Error adding book to cart: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to add books to cart.");
		}
	}

	// Method to apply a promo code
	public void applyPromoCode(String promoCode) {
		if (orderService != null) {
			try {
				orderService.applyPromoCode(promoCode);
			} catch (Exception e) {
				System.out.println("Error applying promo code: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to apply promo codes.");
		}
	}

	// Method to create an order (checkout)
	public void checkout() {
		if (orderService != null) {
			try {
				orderService.createOrder();
			} catch (Exception e) {
				System.out.println("Error creating order: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to create orders.");
		}
	}

	// Method to cancel an order
	public void cancelOrder(int orderId) {
		if (orderService != null) {
			try {
				orderService.cancelOrder(orderId);
				System.out.println("Order cancelled successfully: OrderID = " + orderId);
			} catch (Exception e) {
				System.out.println("Error cancelling order: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to cancel orders.");
		}
	}

	// Method to update order status
	public void updateOrderStatus(int orderId, String newStatus, boolean isAdmin) {
		if (orderService != null) {
			try {
				orderService.updateOrderStatus(orderId, newStatus, isAdmin);
				System.out.println("Order status updated: OrderID = " + orderId + ", New Status = " + newStatus);
			} catch (Exception e) {
				System.out.println("Error updating order status: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to update order status.");
		}
	}

	// Method to check order status
	public void checkOrderStatus(int orderId) {
		if (orderService != null) {
			try {
				orderService.checkOrderStatus(orderId);
				System.out.println("Order status checked for OrderID = " + orderId);
			} catch (Exception e) {
				System.out.println("Error checking order status: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to check order status.");
		}
	}

	// Method to fetch all orders for a user
	public void getAllOrdersForUser() {
		if (orderService != null) {
			try {
				List<Order> orders = orderService.getAllOrdersForUser();
				System.out.println("Orders fetched for current user :");
				for (Order order : orders) {
					System.out.println(order);
				}
			} catch (Exception e) {
				System.out.println("Error fetching orders: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to fetch orders.");
		}
	}

	public void initializeActiveOrder() {
		if (orderService != null) {
			try {
				orderService.initializeActiveOrder();
			} catch (Exception e) {
				System.out.println("Error fetching active orders: " + e.getMessage());
			}
		} else {
			System.out.println("No service available to fetch orders.");
		}
	}
}
