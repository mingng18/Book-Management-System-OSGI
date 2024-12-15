package com.cbse.order.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date createdAt;
    private double totalPrice;
    private String status;
    private List<String> promoIds;
    private List<OrderedBook> books;

    // Constructor
    public Order(int id, Date createdAt, double totalPrice, String status, List<String> promoIds, List<OrderedBook> books) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.status = status;
        this.promoIds = promoIds;
        this.books = books;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public void setPromoIds(List<String> promoIds) {
        this.promoIds = promoIds;
    }

    public List<OrderedBook> getBooks() {
        return books;
    }

    public void setBooks(List<OrderedBook> books) {
        this.books = books;
    }

    // toString Method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", promoIds=" + promoIds +
                ", books=" + books +
                '}';
    }
}
