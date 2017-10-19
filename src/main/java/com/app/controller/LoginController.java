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
import com.app.user.UserService;

@Controller
@SessionAttributes("userID")
public class LoginController {

	@Autowired
	UserService userService = new UserService();
	
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
		else if(userService.getUser(userInSession).getLiaisonOfficer())
			return new ModelAndView("redirect:/officerHome");
		//else if its a LO
		else
			return new ModelAndView("redirect:/operatorHome");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {
		String hashedPassword;
		User user = null;
		
		try {
			hashedPassword = HashController.hash(password);
			user =  userService.getUserByLogin(username, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null){
			return new ModelAndView("redirect:/");
		}
		else{
			model.put("userID", user.getUserID());
			if(user.getLiaisonOfficer())
				return new ModelAndView("redirect:/officerHome");
			else
				return new ModelAndView("redirect:/operatorHome");
		}
	}
}
