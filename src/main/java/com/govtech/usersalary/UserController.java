package com.govtech.usersalary;


import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	private final UserRepository repository;
	private final String controllerMapping="/api/v1";

	UserController(UserRepository repository){
		this.repository = repository;
	}

	@GetMapping("/users")
	ResponseEntity<UserGenericAdvice> all() {
		String path = controllerMapping+"/users";
		try {
			List<User> u = repository.findBySalaryBetween(0.0,4000.0);		
			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.OK,null,"Get Success",path,u)
					,HttpStatus.OK);
		}
		catch(Exception ex){
			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.OK,null,"Post Fail",path)
					,HttpStatus.BAD_REQUEST);	
		}
	}

	@GetMapping("/users/filterSalary/{minSalary}/{maxSalary}")
	ResponseEntity<UserGenericAdvice> filterSalary( @PathVariable double minSalary, @PathVariable double maxSalary) {
		String path = controllerMapping+"/users/filterSalary/"+minSalary+"/"+maxSalary;
		try {
			List<User> u = repository.findBySalaryBetween(minSalary,maxSalary);
			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.OK,null,"Get Success",path,u)
					,HttpStatus.OK);
		}
		catch(Exception ex){
			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.OK,null,"Post Fail",path)
					,HttpStatus.BAD_REQUEST);	
		}
	}

	@PostMapping("/user")
	ResponseEntity<UserGenericAdvice> newUser(@RequestBody User newUser) {
		String path = controllerMapping+"/user";
		try {
			User u = repository.save(newUser);

			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.CREATED,null,"Post Success",path, u)
					,HttpStatus.CREATED);			
		}
		catch(Exception ex){
			return new ResponseEntity<UserGenericAdvice>(
					new UserGenericAdvice(HttpStatus.OK,null,"Post Fail",path)
					,HttpStatus.BAD_REQUEST);	
		}
	}

	// Single item
	@GetMapping("/user/{id}")
	ResponseEntity<UserGenericAdvice> one(@PathVariable Integer id) {
		String path = controllerMapping+"/user/"+id;
		User u = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id,path));

		return new ResponseEntity<UserGenericAdvice>(
				new UserGenericAdvice(HttpStatus.OK,null,"Get Success",path, u)
				,HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	ResponseEntity<UserGenericAdvice> replaceUser(@RequestBody User newUser, @PathVariable Integer id) {
		String path = controllerMapping+"/user/"+id;

		User u = repository.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setSalary(newUser.getSalary());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});

		return new ResponseEntity<UserGenericAdvice>(
				new UserGenericAdvice(HttpStatus.OK,null,"Put Success",path,u)
				,HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	ResponseEntity<UserGenericAdvice> deleteUser(@PathVariable Integer id) {
		repository.deleteById(id);
		String message = "Delete Success";
		String path = controllerMapping+"/user/"+id;
		return new ResponseEntity<UserGenericAdvice>(new UserGenericAdvice(HttpStatus.OK,null,message,path), HttpStatus.OK);
	}

	@PostMapping(value = "/users/upload", consumes = "multipart/form-data")
	public ResponseEntity<UserGenericAdvice> uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
		repository.saveAll(CSVHelper.read(User.class, file.getInputStream()));
		String path = controllerMapping+"/users/upload/";
		return new ResponseEntity<UserGenericAdvice>(new UserGenericAdvice(HttpStatus.OK,null,"Upload Success",path), HttpStatus.OK);
	}

}
