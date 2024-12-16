package com.cbse.book.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cbse.book.model.Book;

public class BookRepository {

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM books WHERE is_deleted = false";

		try (Connection connection = DBUtil.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String genre = resultSet.getString("genre");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				int restockThreshold = resultSet.getInt("restock_threshold");

				Book book = new Book(title, author, genre, price, stockQuantity, restockThreshold);
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching books.");
			e.printStackTrace();
		}

		return books;
	}

	public void addBook(Book book) {
		String query = "INSERT INTO books (title, author, genre, price, stock_quantity, restock_threshold) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getGenre());
			preparedStatement.setDouble(4, book.getPrice());
			preparedStatement.setInt(5, book.getStockQuantity());
			preparedStatement.setInt(6, book.getRestockThreshold());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBook(String title, Book updatedBook) {
		String query = "UPDATE books SET title = ?, author = ?, genre = ?, price = ?, stock_quantity = ?, restock_threshold = ? WHERE title = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, updatedBook.getTitle());
			preparedStatement.setString(2, updatedBook.getAuthor());
			preparedStatement.setString(3, updatedBook.getGenre());
			preparedStatement.setDouble(4, updatedBook.getPrice());
			preparedStatement.setInt(5, updatedBook.getStockQuantity());
			preparedStatement.setInt(6, updatedBook.getRestockThreshold());
			preparedStatement.setString(7, title);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(String title) {
		String query = "UPDATE books SET is_deleted = true WHERE title = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, title);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(String title) {
		String query = "SELECT * FROM books WHERE title = ? AND is_deleted = false";
		Book book = null;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, title);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String bookTitle = resultSet.getString("title");
				String author = resultSet.getString("author");
				String genre = resultSet.getString("genre");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				int restockThreshold = resultSet.getInt("restock_threshold");

				book = new Book(bookTitle, author, genre, price, stockQuantity, restockThreshold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public List<Book> getBestSellingBooks() {
		List<Book> bestSellingBooks = new ArrayList<>();
		String query = "SELECT b.title, b.author, b.price, SUM(ob.quantity) AS total_quantity " + "FROM books b "
				+ "JOIN ordered_books ob ON b.id = ob.book_id " + "GROUP BY b.title, b.author, b.price "
				+ "ORDER BY total_quantity DESC";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				double price = resultSet.getDouble("price");
				int totalQuantity = resultSet.getInt("total_quantity");

				Book book = new Book(title, author, "", price, totalQuantity, 0);
				bestSellingBooks.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching best-selling books.");
			e.printStackTrace();
		}

		return bestSellingBooks;
	}

	public List<Book> getBestEarningBooks() {
		List<Book> bestEarningBooks = new ArrayList<>();
		String query = "SELECT b.title, b.author, b.price, "
				+ "SUM((ob.quantity * b.price) * (1 - COALESCE(p.discount_rate, 0)/ 100)) AS total_earnings "
				+ "FROM books b " + "JOIN ordered_books ob ON b.id = ob.book_id "
				+ "JOIN orders o ON ob.order_id = o.id " + "LEFT JOIN promoted_orders po ON o.id = po.order_id "
				+ "LEFT JOIN promos p ON po.promo_id = p.id " + "GROUP BY b.id, b.title, b.author, b.price "
				+ "ORDER BY total_earnings DESC";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				double price = resultSet.getDouble("total_earnings");

				Book book = new Book(title, author, "", price, 0, 0);
				bestEarningBooks.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching best-earning books.");
			e.printStackTrace();
		}

		return bestEarningBooks;
	}
}
