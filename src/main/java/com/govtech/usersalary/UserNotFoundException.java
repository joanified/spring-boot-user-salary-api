package com.govtech.usersalary;

class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5244951388700819651L;

	String path;

	UserNotFoundException(Integer id, String path) {
		super("Could not find user " + id);
		this.path = path;
	}

}
