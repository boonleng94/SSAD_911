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
		//else go to homepage
		else
			return new ModelAndView("redirect:/home");
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
			model.put("errorMessage", "Please log in!");
			model.put("errorRedirect", "/");
			return new ModelAndView("/warning");
		}
		else{
			model.put("userID", user.getUserID());
			return new ModelAndView("redirect:/home");
		}
	}
}
