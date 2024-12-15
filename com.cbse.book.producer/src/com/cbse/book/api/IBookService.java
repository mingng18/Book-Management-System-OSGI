package com.cbse.book.api;

import java.util.List;

import com.cbse.book.impl.Book;

public interface IBookService {
	void addBook(Book book);

	void updateBook(String title, Book book);

	void deleteBook(String title);

	Book getBook(String title);

	List<Book> getAllBooks();
}
