package main.java.com.intellect.springboot.service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import main.java.com.intellect.springboot.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	private static Map<String, User> users = new HashMap<String, User>();
	
	String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static String dateFormat = "dd-mm-yyyy";
	java.text.SimpleDateFormat dateFormatter = 
            new java.text.SimpleDateFormat( dateFormat );

	static {
		//Initialize Data
		
		User user1 = new User(111111, "Ajmera", "1", 
				true, "pujaajmera9@gmail.com", new Date("15-FEB-1991"), "Puja");
		
		User user2 = new User(111112, "Tavhare", "2", 
				true, "pujadtavhare9@gmail.com", new Date("15-MAR-1991"), "Puja");
		
		users.put("1", user1);
		users.put("2", user2);
	}

	public List<User> retrieveAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();

		for(Map.Entry<String,User> map : users.entrySet()){

		     userList.add(map.getValue());

		}
		return userList;
	}

	public User retrieveUser(String userId) {
		return users.get(userId);
	}

	public String addUser(User user, HttpServletResponse response) {
		if (user == null) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			return "User data Invalid !";
		} else {
			if(user.getId() == null){
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				return "Please provide User Id ";
			} else if(users.get(user.getId()) != null && users.get(user.getId()).isActive()) {
				response.setStatus(HttpStatus.CONFLICT.value());
				return "User Id already present !";
			} 
			if(user.getBirthDate() != null) {
				try {
					if(user.getBirthDate().after(new Date())) {
						response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
						return "Please give valid DOB!!";
					}
					user.setBirthDate(dateFormatter.parse(user.getBirthDate().toString()));
				} catch (ParseException ex) {
					response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
					return "Please give birthdate in dd-MMM-yyyy format !!";
				}
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				return "Please provide Date of birth";
			}
			if(user.getEmail() != null) {
				
			   Boolean isEmailValid = user.getEmail().matches(EMAIL_REGEX);
			   if(!isEmailValid) {
				   response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			 	  return "Given email is not valid";
			   }
				for(User u : retrieveAllUsers()) {
					if(u.getEmail().equals(user.getEmail())) {
						response.setStatus(HttpStatus.CONFLICT.value());
						return "Given e-mail id is already associated with existing user, please give unique email-id";
					}
				}				
			}
			user.setActive(true);
			users.put(user.getId(), user);
		}
		response.setStatus(HttpStatus.CREATED.value());
		return user.getId(); 
	}
	
	public String updateUser(String userId, User user, HttpServletResponse response) {
		User existingUser = null;
		if (user == null) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			return "User data Invalid !";
		} else {
			if(userId == null){
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				return "Please provide User Id ";
			} 
			if(user.getBirthDate() != null) {
				try {
					user.setBirthDate(dateFormatter.parse(user.getBirthDate().toString()));
					if(user.getBirthDate().after(new Date())) {
						response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
						return "Please give valid DOB!!";
					}
				} catch (ParseException ex) {
					response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
					return "Please give birthdate in dd-MMM-yyyy format !!";
				}
			} else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				return "Please provide Date of birth";
			}
			if(user.getEmail() != null) {
				Boolean isEmailValid = user.getEmail().matches(EMAIL_REGEX);
			    if(!isEmailValid) {
			    	response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			    	return "Given email is not valid";
			    }
				for(User u : retrieveAllUsers()) {
					if(u.getEmail().equals(user.getEmail()) && !u.getId().equals(userId)) {
						response.setStatus(HttpStatus.CONFLICT.value());
						return "Given e-mail id is already associated with existing user, please give unique email-id";
					}
				}				
			}
			
			if(users.get(user.getId()) != null && user.isActive()) {
				existingUser = users.get(user.getId());
				if(!existingUser.getFname().equals(user.getFname()) 
						|| ! existingUser.getLname().equals(user.getLname()) 
						|| !existingUser.getEmail().equals(user.getEmail())) {
					
				} else {
					existingUser.setPinCode(user.getPinCode());
					existingUser.setBirthDate(user.getBirthDate());
					users.put(user.getId(), existingUser);
				}
			} else {
				response.setStatus(HttpStatus.NOT_FOUND.value());
				return "User not found";
			}
			
		}
		response.setStatus(HttpStatus.OK.value());
		return "User details updated Successfully"; 
	}

	public String deleteUser(String userId, HttpServletResponse response) {
		User user = users.get(userId);
			if(user != null && user.isActive()) {
				user.setActive(false);
				response.setStatus(HttpStatus.OK.value());
				return "User Id removed successfully !!";
			} 
			response.setStatus(HttpStatus.NOT_FOUND.value());
		return "User does not exist !!"; 
	}
}