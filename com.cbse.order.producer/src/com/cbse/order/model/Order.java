package com.cbse.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Date createdAt;
	private double totalPrice;
	private String status;
	private List<String> promoIds;
	private List<OrderedBook> books;

	public static final String PENDING = "PENDING";
	public static final String ACCEPTED = "ACCEPTED";
	public static final String COMPLETED = "COMPLETED";
	public static final String CANCELLED = "CANCELLED";

	public Order(int id) {
		this.id = id;
		this.createdAt = new Date();
		this.totalPrice = 0.0;
		this.status = PENDING;
		this.promoIds = new ArrayList<>();
		this.books = new ArrayList<>();
	}

	// Getters, Setters, and toString
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getPromoIds() {
		return promoIds;
	}

	public List<OrderedBook> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		return "Order{id=" + id + ", createdAt=" + createdAt + ", totalPrice=" + totalPrice + ", status='" + status
				+ "', promoIds=" + promoIds + ", books=" + books + "}";
	}
}
