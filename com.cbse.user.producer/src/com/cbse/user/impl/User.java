package com.cbse.user.impl;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String email;
	private String username;
	private String password;
	private boolean isLoggedIn;
	private Set<Integer> roles; // A set to handle multiple roles (e.g., Admin, User)

	// Constants for roles
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_USER = 2;

	// Constructor for a new user (default role is User)
	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.isLoggedIn = false;
		this.roles = new HashSet<>();
		this.roles.add(ROLE_USER); // Default role is normal user
	}

	// Getters and Setters
	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		isLoggedIn = loggedIn;
	}

	public boolean checkPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}

	public String getPassword() {
		return password;
	}

	// Role management methods
	public Set<Integer> getRoles() {
		return roles;
	}

	public void addRole(int role) {
		roles.add(role);
	}

	public void removeRole(int role) {
		roles.remove(role);
	}

	public boolean hasRole(int role) {
		return roles.contains(role);
	}
}
