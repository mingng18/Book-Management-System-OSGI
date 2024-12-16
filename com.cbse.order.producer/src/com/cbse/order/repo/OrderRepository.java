package com.cbse.order.repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cbse.order.model.Order;
import com.cbse.order.model.OrderedBook;

public class OrderRepository {

	// Add a book to the cart (ordered_books table)
	public void addBookToCart(int bookId, int orderId, int quantity) {
		String query = "INSERT INTO ordered_books (book_id, order_id, quantity, created_at) VALUES (?, ?, ?, NOW())";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, bookId);
			stmt.setInt(2, orderId);
			stmt.setInt(3, quantity);

			stmt.executeUpdate();
			System.out.println("Book added to cart successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Apply promo code (promoted_order table)
	public void applyPromoCode(int orderId, int promoId) {
		String query = "INSERT INTO promoted_orders (order_id, promo_id, created_at, updated_at) VALUES (?, ?, NOW(), NOW())";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, orderId);
			stmt.setInt(2, promoId);

			stmt.executeUpdate();
			System.out.println("Promo code applied successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Create order (orders table)
	public int createOrder(int userId, double totalPrice) {
		String query = "INSERT INTO orders (user_id, total_price, status, created_at, is_deleted) VALUES (?, ?, ?, NOW(), 0)";
		int orderId = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, userId);
			stmt.setDouble(2, totalPrice);
			stmt.setString(3, "PENDING");

			stmt.executeUpdate();

			// Retrieve the auto-generated order ID
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
			System.out.println("Order created successfully with ID: " + orderId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}

	// Update order status (orders table)
	public void updateOrderStatus(int orderId, String newStatus) {
		String query = "UPDATE orders SET status = ? WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, newStatus);
			stmt.setInt(2, orderId);

			stmt.executeUpdate();
			System.out.println("Order status updated to: " + newStatus);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Cancel order (orders table)
	public void cancelOrder(int orderId) {
		String query = "UPDATE orders SET status = 'CANCELLED' WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, orderId);

			stmt.executeUpdate();
			System.out.println("Order cancelled successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Calculate total price based on ordered_books
	public double calculateTotalPrice(int orderId) {
		String query = "SELECT SUM(quantity * 10.0) AS total FROM ordered_books WHERE order_id = ?";
		double totalPrice = 0.0;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				totalPrice = rs.getDouble("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPrice;
	}

	// Get the current order status
	public String getOrderStatus(int orderId) {
		String query = "SELECT status FROM orders WHERE id = ?";
		String status = null;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				status = rs.getString("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public Order getUnpaidOrder(int userId) {
		String query = "SELECT * FROM orders WHERE user_id = ? AND status = 'PENDING' LIMIT 1";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Order order = mapOrder(rs);
				loadPromoCodesForOrder(order, conn);
				loadOrderedBooksForOrder(order, conn);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Retrieve all orders for a user (with promo codes and books)
	public List<Order> getAllOrdersForUser(int userId) {
		List<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders WHERE user_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Order order = mapOrder(rs);
				loadPromoCodesForOrder(order, conn);
				loadOrderedBooksForOrder(order, conn);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	// Helper method to map a ResultSet row to an Order object
	private Order mapOrder(ResultSet rs) throws SQLException {
		Order order = new Order(rs.getInt("id"));
		order.setTotalPrice(rs.getDouble("total_price"));
		order.setStatus(rs.getString("status"));
		return order;
	}

	// Load promo codes for an order
	private void loadPromoCodesForOrder(Order order, Connection conn) throws SQLException {
		String promoQuery = "SELECT promo_id FROM promoted_orders WHERE order_id = ?";
		try (PreparedStatement promoStmt = conn.prepareStatement(promoQuery)) {
			promoStmt.setInt(1, order.getId());
			ResultSet promoRs = promoStmt.executeQuery();
			while (promoRs.next()) {
				order.getPromoIds().add(promoRs.getString("promo_id"));
			}
		}
	}

	// Load ordered books for an order
	private void loadOrderedBooksForOrder(Order order, Connection conn) throws SQLException {
		String booksQuery = "SELECT id, book_id, quantity FROM ordered_books WHERE order_id = ?";
		try (PreparedStatement bookStmt = conn.prepareStatement(booksQuery)) {
			bookStmt.setInt(1, order.getId());
			ResultSet bookRs = bookStmt.executeQuery();
			while (bookRs.next()) {
				OrderedBook book = new OrderedBook(bookRs.getInt("id"), bookRs.getInt("book_id"),
						bookRs.getInt("quantity"));
				order.getBooks().add(book);
			}
		}
	}
}
