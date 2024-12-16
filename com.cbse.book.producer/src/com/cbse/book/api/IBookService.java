package com.cbse.book.api;

import java.util.List;

import com.cbse.book.model.Book;

public interface IBookService {
	void addBook(Book book);

	void updateBook(String title, Book book);

	void deleteBook(String title);

	Book getBook(String title);

	List<Book> getAllBooks();

	List<Book> getBestSellingBooks();

	List<Book> getBestEarningBooks();
}
