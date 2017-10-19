package com.app.controller;

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

	@RequestMapping("/home")
	public ModelAndView home(ModelMap model) {
		if(model.get("userID") == null){
//			model.put("errorMessage", "Please log in!");
//			model.put("errorRedirect", "/");
			return new ModelAndView("redirect:/warning", model);
		}
		else if(UserController.validateUserAccess((int) model.get("userID")))
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