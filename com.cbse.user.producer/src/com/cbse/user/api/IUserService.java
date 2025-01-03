package com.cbse.user.api;

import com.cbse.user.model.User;

public interface IUserService {

	// User management
	void signUp(String email, String username, String password);

	User login(String email, String password);

	void logout(String email);

	boolean userIsAdmin();
	
	User getCurrentUser();
}
