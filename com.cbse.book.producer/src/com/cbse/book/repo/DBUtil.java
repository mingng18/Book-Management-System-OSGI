package com.cbse.book.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

//	private static final String URL = "jdbc:mysql://localhost:3306/book_management_system";
//	private static final String USER = "root";
//	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {

//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("Connecting...");
//		return DriverManager.getConnection(URL, USER, PASSWORD);

		String url = "jdbc:mysql://localhost:3306/book_management_system?autoReconnect=true";
		String username = "root";
		String password = "";

//		System.out.println("Loading driver ...");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}

//		System.out.println("Connecting database ...");

		return DriverManager.getConnection(url, username, password);
	}
}
