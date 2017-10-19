package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.user.UserController;

//ANNOTATE CONTROLLER TO LET MAVEN TREAT IT LIKE A CONTROLLER
@Controller
@SessionAttributes("userID")
public class HomePageController {

	@Autowired
	UserController userController = new UserController();
	
	@RequestMapping("/home")
	public ModelAndView home(ModelMap model) {
		if(model.get("userID") == null){
			model.put("errorMessage", "Please log in!");
			model.put("errorRedirect", "/");
			return new ModelAndView("/warning");
		}
		else if(userController.validateUserAccess((int) model.get("userID")))
			return new ModelAndView("officerhome");
		else
			return new ModelAndView("operatorhome");
	}
}