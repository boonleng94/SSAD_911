package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD:src/main/java/com/app/controller/LoginPageController.java
<<<<<<< HEAD:src/main/java/com/app/controller/LoginPageController.java
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
		else if(UserController.validateUserAccess(userInSession))
			return new ModelAndView("redirect:/officerHome");
		//else if its a LO
		else
			return new ModelAndView("redirect:/operatorHome");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {

		User user = null;
		
		try {
			String hashedPassword = HashController.hash(password);
			user =  UserController.getUserByUsernameAndHashedPassword(username, hashedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user == null){
			return new ModelAndView("redirect:/");
		}
		else{
			model.put("userID", user.getUserID());
//			if(user.getliaisonOfficer())
//				return new ModelAndView("redirect:/officerHome");
//			else
				return new ModelAndView("redirect:/home");
		}
=======
@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
>>>>>>> parent of 9c66c41... login functions mostly done + report basic functionalities done:src/main/java/com/app/controller/LoginController.java
=======
@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
>>>>>>> parent of 9c66c41... login functions mostly done + report basic functionalities done:src/main/java/com/app/controller/LoginController.java
	}
}
