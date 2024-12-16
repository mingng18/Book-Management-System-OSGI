package com.cbse.analysis.impl;

import java.util.List;

import com.cbse.analysis.api.IAnalysisService;
import com.cbse.book.api.IBookService;
import com.cbse.book.model.Book;
import com.cbse.user.api.IUserService;

public class AnalysisServiceImpl implements IAnalysisService {

	private final IBookService bookService;
	private final IUserService userService;

	// Constructor Injection
	public AnalysisServiceImpl(IBookService bookService, IUserService userService) {
		this.bookService = bookService;
		this.userService = userService;
	}

	// Helper method to check if the current user is an admin (retrieve user info
	// from session)
	private boolean isAdmin() {
		return userService.userIsAdmin();
	}

	@Override
	public List<Book> getAllBookStock() {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can view analysis.");
		}
		return bookService.getAllBooks();
	}

	@Override
	public List<Book> getBestSellingBooks() {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can view analysis.");
		}
		return bookService.getBestSellingBooks();
	}
	
	@Override
	public List<Book> getBestEarningBooks() {
		if (!isAdmin()) {
			throw new SecurityException("Only admins can view analysis.");
		}
		return bookService.getBestEarningBooks();
	}

}
