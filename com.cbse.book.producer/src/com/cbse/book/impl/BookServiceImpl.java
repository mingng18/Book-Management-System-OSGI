package com.cbse.book.impl;

import java.util.List;

import com.cbse.book.api.IBookService;
import com.cbse.book.model.Book;
import com.cbse.book.repo.BookRepository;
import com.cbse.user.api.IUserService;

public class BookServiceImpl implements IBookService {

	private final BookRepository bookRepository;
	private final IUserService userService;

	// Constructor Injection
	public BookServiceImpl(IUserService userService) {
		bookRepository = new BookRepository();
		this.userService = userService;
	}

	// Helper method to check if the current user is an admin (retrieve user info
	// from session)
	private boolean isAdmin() {
		return userService.userIsAdmin();
	}

	@Override
	public void addBook(Book book) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can add books.");
		}
		bookRepository.addBook(book);
		System.out.println("Book added: " + book.getTitle());
	}

	@Override
	public void updateBook(String title, Book updatedBook) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can update books.");
		}
		bookRepository.updateBook(title, updatedBook);
		System.out.println("Book updated successfully: " + updatedBook.getTitle());
	}

	@Override
	public void deleteBook(String title) {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can delete books.");
		}
		bookRepository.deleteBook(title);
		System.out.println("Book marked as deleted: " + title);
	}

	@Override
	public Book getBook(String title) {
		return bookRepository.getBook(title);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.getAllBooks();
	}

	@Override
	public List<Book> getBestSellingBooks() {
		return bookRepository.getBestSellingBooks();
	}

	@Override
	public List<Book> getBestEarningBooks() {
		return bookRepository.getBestEarningBooks();
	}
}