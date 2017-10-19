package com.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	//ALWAYS RMB TO AUTOWIRE SERVICES THAT CONNECTS TO REPOSITORIES!!!!
	@Autowired
	private UserService userService;
	
	//check if user is LO or not via userID
	public boolean validateUserAccess(int userInSession){
		return userService.getUser(userInSession).getLiaisonOfficer();
	}
	 
	//return User via username and hashedpassword
	public User getUserByUsernameAndHashedPassword(String username, String hashedPassword){
		return userService.getUserByLogin(username, hashedPassword);
	}
	
	//return User via userID
	public User getUserByUserID(int userID){
		return userService.getUserByUserID(userID);
	}
}