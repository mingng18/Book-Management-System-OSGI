package com.cbse.book.impl;

public class Book {
	private String title;
	private String author;
	private String genre;
	private double price;
	private int stockQuantity;
	private int restockThreshold;
	private boolean isDeleted;

	public Book() {
	}

	public Book(String title, String author, String genre, double price, int stockQuantity, int restockThreshold) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.restockThreshold = restockThreshold;
		this.isDeleted = false; // Default to false when a book is created
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getRestockThreshold() {
		return restockThreshold;
	}

	public void setRestockThreshold(int restockThreshold) {
		this.restockThreshold = restockThreshold;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", genre='" + genre + '\''
				+ ", price=" + price + ", stockQuantity=" + stockQuantity + ", restockThreshold=" + restockThreshold
				+ ", isDeleted=" + isDeleted + '}';
	}
}
