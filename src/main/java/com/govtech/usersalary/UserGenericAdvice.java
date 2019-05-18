package com.govtech.usersalary;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserGenericAdvice {

	private User result;
	private List<User> results;
	private String timestamp;
	private HttpStatus status;
	private String error;
	private String message;
	private String path;

	UserGenericAdvice() {}
	
	UserGenericAdvice(HttpStatus status, String error, String message, String path) {
		this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	UserGenericAdvice(HttpStatus status, String error, String message, String path,User result) {
		this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.result = result;
	} 
	
	UserGenericAdvice(HttpStatus status, String error, String message, String path,List<User> results) {
		this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.results = results;
	} 
}
