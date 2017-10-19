package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.user.User;
import com.app.user.UserController;
import com.app.user.UserService;

@Controller
@SessionAttributes("userID")
public class LoginPageController {

	@Autowired
	UserController userController = new UserController();
	
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
		//else if(userController.validateUserAccess(userInSession))
		else
			return new ModelAndView("redirect:/home");
		//else if its a LO
		//else
		//	return new ModelAndView("redirect:/operatorHome");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {
		String hashedPassword;
		User user = null;
		
		try {
			hashedPassword = HashController.hash(password);
			user =  userController.getUserByUsernameAndHashedPassword(username, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null){
			//NEED DYNAMIC WARNING / ERROR PAGE
			model.put("errorMessage", "Please log in!");
			model.put("errorRedirect", "/");
			return new ModelAndView("redirect:/warning");
		}
		else{
			model.put("userID", user.getUserID());
//			if(user.getLiaisonOfficer())
				return new ModelAndView("redirect:/home");
//			else
//				return new ModelAndView("redirect:/operatorHome");
		}
	}
}
