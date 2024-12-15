package com.cbse.order.model;

public class OrderedBook {
    private int id;
    private int bookId;
    private int quantity;

    // Constructor
    public OrderedBook(int id, int bookId, int quantity) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString Method
    @Override
    public String toString() {
        return "OrderedBook{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }
}
