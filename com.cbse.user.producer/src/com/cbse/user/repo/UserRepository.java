package com.cbse.user.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cbse.user.model.User;

public class UserRepository {


	// Add a new user (default role = 2, normal user)
	public void addUser(User user) {
		String userQuery = "INSERT INTO users (email, name, hashed_password, created_at, updated_at, is_deleted) VALUES (?, ?, ?, ?, ?, false)";
		String roleQuery = "INSERT INTO user_roles (user_id, role_id) VALUES (?, 2)";

		try (Connection connection = DBUtil.getConnection()) {
			connection.setAutoCommit(false); // Begin transaction

			try (PreparedStatement userStatement = connection.prepareStatement(userQuery,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				userStatement.setString(1, user.getEmail());
				userStatement.setString(2, user.getUsername());
				userStatement.setString(3, user.getPassword());
				java.util.Date currentDate = new java.util.Date();
				userStatement.setTimestamp(4, new java.sql.Timestamp(currentDate.getTime())); // created_at
				userStatement.setTimestamp(5, new java.sql.Timestamp(currentDate.getTime())); // updated_at

				userStatement.executeUpdate();

				// Retrieve generated user_id
				ResultSet generatedKeys = userStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					int userId = generatedKeys.getInt(1);
					user.setId(userId); // Set the generated ID to the User object

					// Insert default role into user_roles table
					try (PreparedStatement roleStatement = connection.prepareStatement(roleQuery)) {
						roleStatement.setInt(1, userId);
						roleStatement.executeUpdate();
					}
				}

				connection.commit(); // Commit transaction
			} catch (SQLException e) {
				connection.rollback(); // Rollback in case of failure
				throw e;
			}
		} catch (SQLException e) {
			System.out.println("Error adding user.");
			e.printStackTrace();
		}
	}

	// Update user details
	public void updateUser(String email, User updatedUser) {
		String query = "UPDATE users SET name = ?, hashed_password = ? WHERE email = ? AND is_deleted = false";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, updatedUser.getUsername());
			preparedStatement.setString(2, updatedUser.getPassword());
			preparedStatement.setString(3, email);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error updating user.");
			e.printStackTrace();
		}
	}

	// Soft delete a user by email
	public void deleteUser(String email) {
		String query = "UPDATE users SET is_deleted = true WHERE email = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, email);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error deleting user.");
			e.printStackTrace();
		}
	}

	// Get a specific user by email
	public User getUserByEmail(String email) {
		String query = "SELECT id, email, name, hashed_password FROM users WHERE email = ? AND is_deleted = false";
		User user = null;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id"); // Fetch user ID
				String username = resultSet.getString("name");
				String password = resultSet.getString("hashed_password");

				user = new User(id, email, username, password); // Pass ID to constructor
			}
		} catch (SQLException e) {
			System.out.println("Error fetching user by email.");
			e.printStackTrace();
		}

		return user;
	}

	public boolean userHasRole(String email, int roleId) {
		String query = "SELECT ur.role_id FROM user_roles ur " + "JOIN users u ON ur.user_id = u.id "
				+ "WHERE u.email = ? AND ur.role_id = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			// Set parameters
			preparedStatement.setString(1, email); // Set the email of the user
			preparedStatement.setInt(2, roleId); // Set the role ID to check

			// Execute the query
			ResultSet resultSet = preparedStatement.executeQuery();

			// If a record is found, the user has the role
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // Return false if no matching role is found for the user
	}

}
