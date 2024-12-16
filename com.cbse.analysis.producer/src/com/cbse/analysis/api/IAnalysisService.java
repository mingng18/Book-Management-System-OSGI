package com.cbse.analysis.api;

import java.util.List;

import com.cbse.book.model.Book;

public interface IAnalysisService {
	List<Book> getAllBookStock();

	List<Book> getBestSellingBooks();
	
	List<Book> getBestEarningBooks();
}
