package com.cbse.book.impl;

//import java.util.ArrayList;
import java.util.List;

import com.cbse.book.api.IBookService;
import com.cbse.book.repo.BookRepository;

public class BookServiceImpl implements IBookService {

//	private List<Book> bookList = new ArrayList<>();
//	@Override
//	public void addBook(Book book) {
//		bookList.add(book);
//		System.out.println("Book added: " + book.getTitle());
//	}
//
//	@Override
//	public void updateBook(String title, Book updatedBook) {
//		for (Book book : bookList) {
//			if (book.getTitle().equalsIgnoreCase(title) && !book.isDeleted()) {
//				book.setTitle(updatedBook.getTitle());
//				book.setAuthor(updatedBook.getAuthor());
//				book.setGenre(updatedBook.getGenre());
//				book.setPrice(updatedBook.getPrice());
//				book.setStockQuantity(updatedBook.getStockQuantity());
//				book.setRestockThreshold(updatedBook.getRestockThreshold());
//				System.out.println("Book updated successfully: " + updatedBook.getTitle());
//				return;
//			}
//		}
//		System.out.println("Book not found: " + title);
//	}
//
//	@Override
//	public void deleteBook(String title) {
//		for (Book book : bookList) {
//			if (book.getTitle().equalsIgnoreCase(title) && !book.isDeleted()) {
//				book.setDeleted(true);
//				System.out.println("Book marked as deleted: " + book.getTitle());
//				return;
//			}
//		}
//		System.out.println("Book not found: " + title);
//	}
//
//	@Override
//	public Book getBook(String title) {
//		for (Book book : bookList) {
//			if (book.getTitle().equalsIgnoreCase(title) && !book.isDeleted()) {
//				return book;
//			}
//		}
//		return null;
//	}

//	@Override
//	public List<Book> getAllBooks() {
//		List<Book> activeBooks = new ArrayList<>();
//		for (Book book : bookList) {
//			if (!book.isDeleted()) {
//				activeBooks.add(book);
//			}
//		}
//		return activeBooks;
//	}

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