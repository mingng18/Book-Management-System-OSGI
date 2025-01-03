package com.cbse.user.consumer;

import com.cbse.user.api.IUserService;
import com.cbse.user.model.User;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class UserClient {
	private IUserService userService;

	public UserClient(BundleContext context) {
		ServiceReference<IUserService> serviceRef = context.getServiceReference(IUserService.class);
		if (serviceRef != null) {
			userService = context.getService(serviceRef);
			System.out.println("UserService retrieved successfully.");
		} else {
			System.out.println("UserService not available.");
		}
	}

	// Signup method
	public void signup(String email, String username, String password) {
		if (userService != null) {
			System.out.println("Signing up user...");
			userService.signUp(email, username, password); // Use the signUp method
			System.out.println("User signed up successfully!");
		} else {
			System.out.println("No service available to sign up users.");
		}
	}

	// Login method
	public void login(String email, String password) {
		if (userService != null) {
			System.out.println("Logging in...");
			User user = userService.login(email, password); // Login method returns the user object
			if (user != null) {
				System.out.println("User logged in: " + user.getUsername());
			} else {
				System.out.println("Invalid email or password.");
			}
		} else {
			System.out.println("No service available for login.");
		}
	}

	// Logout method
	public void logout(String email) {
		if (userService != null) {
			System.out.println("Logging out...");
			userService.logout(email); // Pass email for logout
			System.out.println("User logged out.");
		} else {
			System.out.println("No service available for logout.");
		}
	}

	public void getCurrentUser() {
		if (userService != null) {
			System.out.println("Current User is " + userService.getCurrentUser());
		} else {
			System.out.println("No service available for logout.");
		}
	}

}
