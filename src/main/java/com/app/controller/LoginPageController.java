package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.user.User;
import com.app.user.UserController;

@Controller
@SessionAttributes("userID")
public class LoginPageController {

//	@Autowired
//	UserService userService = new UserService();
	
	@RequestMapping("/")
	public ModelAndView firstPage(ModelMap model) {
		int userInSession = 0;
		
		if(model.get("userID") != null) {
			userInSession = (int) model.get("userID");
		}

		//if session has user
		if(userInSession == 0)
			return new ModelAndView("login");
		//else if its not a LO
<<<<<<< HEAD:src/main/java/com/app/controller/LoginPageController.java
		else if(UserController.validateUserAccess(userInSession))
=======
		else if(userService.getUser(userInSession).getLiaisonOfficer())
>>>>>>> 7238cc37ce2510a35f38da9c297c0e1d4c762a41:src/main/java/com/app/controller/LoginController.java
			return new ModelAndView("redirect:/officerHome");
		//else if its a LO
		else
			return new ModelAndView("redirect:/operatorHome");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {
<<<<<<< HEAD:src/main/java/com/app/controller/LoginPageController.java

=======
		String hashedPassword;
>>>>>>> 7238cc37ce2510a35f38da9c297c0e1d4c762a41:src/main/java/com/app/controller/LoginController.java
		User user = null;
		
		try {
			String hashedPassword = HashController.hash(password);
			user =  UserController.getUserByUsernameAndHashedPassword(username, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null){
			return new ModelAndView("redirect:/");
		}
		else{
			model.put("userID", user.getUserID());
<<<<<<< HEAD:src/main/java/com/app/controller/LoginPageController.java
//			if(user.getliaisonOfficer())
//				return new ModelAndView("redirect:/officerHome");
//			else
				return new ModelAndView("redirect:/home");
=======
			if(user.getLiaisonOfficer())
				return new ModelAndView("redirect:/officerHome");
			else
				return new ModelAndView("redirect:/operatorHome");
>>>>>>> 7238cc37ce2510a35f38da9c297c0e1d4c762a41:src/main/java/com/app/controller/LoginController.java
		}
	}
}
