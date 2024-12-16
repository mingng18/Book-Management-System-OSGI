package com.cbse.analysis.consumer;

import com.cbse.analysis.api.IAnalysisService;
import com.cbse.book.model.Book;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AnalysisClient {

	private IAnalysisService analysisService;

	// Constructor Injection
	public AnalysisClient(BundleContext context) {
		// Lookup the IBookService from the OSGi service registry
		ServiceReference<IAnalysisService> serviceRef = context.getServiceReference(IAnalysisService.class);
		if (serviceRef != null) {
			analysisService = context.getService(serviceRef);
			System.out.println("BookService retrieved successfully.");
		} else {
			System.out.println("BookService not available.");
		}

	}

	// Method to fetch and display all book stock
	public void displayAllBookStock() {
		try {
			List<Book> books = analysisService.getAllBookStock();
			System.out.println("All Books:");
			books.forEach(book -> System.out
					.println("Title: " + book.getTitle() + ", Stock Quantity: " + book.getStockQuantity()));
		} catch (SecurityException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Method to fetch and display best-selling books
	public void displayBestSellingBooks() {
		try {
			List<Book> bestSellingBooks = analysisService.getBestSellingBooks();
			System.out.println("Best Selling Books:");
			bestSellingBooks.forEach(book -> System.out
					.println("Title: " + book.getTitle() + ", Quantity Sold: " + book.getStockQuantity()));
		} catch (SecurityException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Method to fetch and display best-selling books
	public void displayBestEarningBooks() {
		try {
			List<Book> bestSellingBooks = analysisService.getBestEarningBooks();
			System.out.println("Best Selling Books:");
			bestSellingBooks.forEach(
					book -> System.out.println("Title: " + book.getTitle() + ", Total Price Sold: " + book.getPrice()));
		} catch (SecurityException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
