package com.cbse.book.consumer;

import com.cbse.book.api.IBookService;
import com.cbse.book.impl.Book;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class BookClient {
	private IBookService bookService;

	public BookClient(BundleContext context) {
		// Lookup the IBookService from the OSGi service registry
		ServiceReference<IBookService> serviceRef = context.getServiceReference(IBookService.class);
		if (serviceRef != null) {
			bookService = context.getService(serviceRef);
			System.out.println("BookService retrieved successfully.");
		} else {
			System.out.println("BookService not available.");
		}

	}

	public void listBooks() {
		if (bookService != null) {
			System.out.println("Listing books...");
			for (Book book : bookService.getAllBooks()) {
				System.out.println(
						"Name: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre());
			}
		} else {
			System.out.println("No service available to list books.");
		}
	}

	public void addBook(Book newBook) {
		if (bookService != null) {
			System.out.println("Adding book...");
			bookService.addBook(newBook);
			System.out.println("Book added!");

		} else {
			System.out.println("No service available to add books.");
		}
	}

}
