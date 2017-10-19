package com.app.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	//ALWAYS RMB TO AUTOWIRE SERVICES THAT CONNECTS TO REPOSITORIES!!!!
	@Autowired
	private static UserService userService;
	
	@RequestMapping("/user")
	public List<User> returnAllUser(){
		return userService.getAllUsers();
	}
	
	//curly braces for inputs
	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable int id){ //need use @pathvariable. convention to keep names same
		return userService.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user")
	public void addUser(@RequestBody User users){
		userService.addUser(users);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/user/{id}")
	public void updateUser(@RequestBody User user, @PathVariable int id){
		userService.updateUser(id, user);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/user/{id}")
	public void deleteUser(@PathVariable String id){
		userService.deleteUser(id);
	}
	
	//STATIC METHODS
	//check if user is LO or not via userID
	public static boolean validateUserAccess(int userInSession){
		return userService.getUser(userInSession).getLiaisonOfficer();
	}

	//return User via username and hashedpassword
	public static User getUserByUsernameAndHashedPassword(String username, String hashedPassword){
		return userService.getUserByLogin(username, hashedPassword);
//		return userService.getUser(1001);
	}
}