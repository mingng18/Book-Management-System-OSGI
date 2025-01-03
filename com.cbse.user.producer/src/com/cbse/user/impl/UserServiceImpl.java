package com.cbse.user.impl;

import com.cbse.user.api.IUserService;
import com.cbse.user.model.User;
import com.cbse.user.repo.UserRepository;

public class UserServiceImpl implements IUserService {

	private UserRepository userRepository = new UserRepository();
	private User currentUser = null; // Current user is initially null (lazy initialization)

	@Override
	public void signUp(String email, String username, String password) {
		// Check if the user already exists
		if (userRepository.getUserByEmail(email) != null) {
			System.out.println("Error: User with this email already exists.");
		} else {
			// Add the new user and assign the default role (2 = normal user)
			User newUser = new User(-1, email, username, password);
			userRepository.addUser(newUser); // Pass role ID 2 for "normal user"
			System.out.println("User signed up successfully: " + username);
		}
	}

	@Override
	public User login(String email, String password) {
		// Check if there's an already logged-in user
		if (currentUser != null) {
			System.out.println("A user is already logged in: " + currentUser.getUsername());
			return null; // Only one user can be logged in at a time
		}

		// Fetch the user from the repository and verify credentials
		User user = userRepository.getUserByEmail(email);
		if (user != null && user.checkPassword(password)) {
			user.setLoggedIn(true); // Mark the user as logged in
			currentUser = user; // Set the current logged-in user
			System.out.println("User logged in: " + user.getUsername());
			return user;
		} else {
			System.out.println("Invalid email or password.");
			return null; // Return null if login fails
		}
	}

	@Override
	public void logout(String email) {
		if (currentUser != null && currentUser.getEmail().equals(email)) {
			currentUser.setLoggedIn(false); // Mark the user as logged out
			System.out.println("User logged out: " + currentUser.getUsername());
			currentUser = null; // Reset the current user to null after logout
		} else {
			System.out.println("No user is currently logged in with email: " + email);
		}
	}

	@Override
	public boolean userIsAdmin() {
		// Check if the current user is logged in
		if (currentUser == null) {
			System.out.println("No user is logged in.");
			return false; // No user logged in
		}
//		System.out.println("Current role " + currentUser.getRoles());

		return userRepository.userHasRole(currentUser.getEmail(), 1);
	}

	// Get the current logged-in user (returns null if no user is logged in)
	@Override
	public User getCurrentUser() {
		return currentUser; // Return null if no user is logged in
	}
}
