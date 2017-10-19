package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			model.put("errorMessage", new String("Please log in!"));
			model.put("errorRedirect", new String("/"));
			return new ModelAndView("redirect:/warning");
		}
		else if(userController.validateUserAccess((int) model.get("userID")))
//		else if(UserController.getUser((int) model.get("userID")) == null)
			return new ModelAndView("officerhome");
		else
			return new ModelAndView("operatorhome");
	}
	
//	@RequestMapping("/officerHome")
//	public ModelAndView officerHome(ModelMap model) {
//		
//		return new ModelAndView("officerhome");
//	}
//
//	@RequestMapping("/operatorHome")
//	public ModelAndView operatorHome(ModelMap model) {
//		return new ModelAndView("operatorhome");
//	}
	
	//TO CHECK IF SESSION ATTR CAN BE PASSED THROUGH OTHER CONTROLLERS
//	@RequestMapping(value = "/operatorHome", method=RequestMethod.POST)
//	public ModelAndView officerHomeredirect(ModelMap model) {
//		return new ModelAndView("redirect:/officerHome");
//	}
}