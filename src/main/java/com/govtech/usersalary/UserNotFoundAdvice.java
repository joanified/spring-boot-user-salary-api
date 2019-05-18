package com.govtech.usersalary;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UserNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	UserGenericAdvice userNotFoundHandler(UserNotFoundException ex, String path) {
		UserGenericAdvice advice = new UserGenericAdvice(HttpStatus.NOT_FOUND, ex.getMessage(), "User Not Found",  ex.path);
		return advice;
	}
}
