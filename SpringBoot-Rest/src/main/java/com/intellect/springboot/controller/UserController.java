package main.java.com.intellect.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import main.java.com.intellect.springboot.model.User;
import main.java.com.intellect.springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}

	@GetMapping("/users/{userId}")
	public User retrieveAllUsers(@PathVariable String userId) {
		return userService.retrieveUser(userId);
	}
	
	@PostMapping("/users")
	public String addUser(@RequestBody User user, HttpServletResponse response) {

		return userService.addUser(user, response);
	}
	
	@DeleteMapping("/users/{userId}/delele")
	public String updateUser(@PathVariable String userId, @RequestBody User user, HttpServletResponse response) {

		return userService.updateUser(userId, user, response);
	}

}
