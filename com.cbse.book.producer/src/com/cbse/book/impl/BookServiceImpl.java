package com.cbse.book.impl;

//import java.util.ArrayList;
import java.util.List;

import com.cbse.book.api.IBookService;
import com.cbse.book.model.Book;
import com.cbse.book.repo.BookRepository;

public class BookServiceImpl implements IBookService {

	private BookRepository bookRepository = new BookRepository();

	@Override
	public void addBook(Book book) {
		bookRepository.addBook(book);
		System.out.println("Book added: " + book.getTitle());
	}

	@Override
	public void updateBook(String title, Book updatedBook) {
		bookRepository.updateBook(title, updatedBook);
		System.out.println("Book updated successfully: " + updatedBook.getTitle());
	}

	@Override
	public void deleteBook(String title) {
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
}