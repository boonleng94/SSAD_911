package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.user.User;
import com.app.user.UserController;

@Controller
@SessionAttributes("userID")
public class WebpageController implements ErrorController{
	@Autowired
	UserController userController = new UserController();
	User user = new User();
	private static final String ERRORPATH = "/error";
	
	/**
	 * Mapping for main page
	 * If user is logged in, redirect to home page
	 * Else, redirect to login page
	 */
	@RequestMapping("/")
	public ModelAndView mainPage(ModelMap model) {
		int userInSession = 0;
		
		if(model.get("userID") != null) {
			userInSession = (int) model.get("userID");
		}

		//if session has user
		if(userInSession == 0)
			return new ModelAndView("login");
		//else go to homepage
		else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	/**
	 * Mapping for home page
	 * If user is a liaison officer, redirect to officer home page
	 * Else, redirect to operator home page
	 */
	@RequestMapping("/home")
	public ModelAndView homePage(ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		} 
		
		else {
			model.put("name", user.getName());
			if (userController.isLiaisonOfficer((int) model.get("userID")))
				return new ModelAndView("officerhome");
			else
				return new ModelAndView("operatorhome");
		}

	}
	
	/**
	 * Mapping for new report page
	 * Used by operator for creating a new emergency report upon receiving 911 call
	 * And inserting into the database
	 * ZH: ONLY OPERATOR CAN CREATE REPORT
	 */
	@RequestMapping("/newReport")
	public ModelAndView reportPage(ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		
		if (userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "You are not authorized. Only operators are allowed to create new reports.");
			model.put("redirect", "/home");
			return new ModelAndView("/message");
		}
		else {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			return new ModelAndView("newreport");
		}
	}

	/**
	 * Mapping for displaying selected report page
	 * Used for displaying selected reports to edit / authenticate
	 * NEED KNOW HOW DOES UI INTERACTS (SEND REPORT ID OR WHOLE REPORT?)
	 */
	@RequestMapping(value = "/editReport", method=RequestMethod.POST)
	public ModelAndView editReport(@RequestBody User user, ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		
		if (userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "You are not authorized. Only operators are allowed to create new reports.");
			model.put("redirect", "/home");
			return new ModelAndView("/message");
		}
		else {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			//STORE POST DATA INTO MODEL
			return new ModelAndView("newreport");
		}
	}
	
	/**
	 * Mapping for error page
	 */
	@RequestMapping(ERRORPATH)
	public ModelAndView errorPage(ModelMap model) {
		model.put("message", "This page is not available.");
		model.put("redirect", "/");
		return new ModelAndView("message");
	}
	
	@Override
	public String getErrorPath() {
		return ERRORPATH;
	}

	/**
	 * Mapping for login request
	 * Authenticate user credentials with database
	 * If successful, redirect to home page
	 * Else, display message and redirect to main page
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {
		String hashedPassword;
		
		try {
			hashedPassword = HashController.hash(password);
			user =  userController.getUserByUsernameAndHashedPassword(username, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null){
			model.put("message", "Invalid Credentials. Please re-login again.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			model.put("userID", user.getUserID());
			return new ModelAndView("redirect:/home");
		}
	}
	
	/**
	 * Mapping for logout request
	 * Set session userID to null, redirect to main page
	 */
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ModelAndView handleLogoutRequest(ModelMap model) {
		model.put("userID", 0);
		model.put("message", "Logged out successfully");
		model.put("redirect", "/");
		return new ModelAndView("/message");
	}
}
