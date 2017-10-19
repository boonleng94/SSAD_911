package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

//ANNOTATE CONTROLLER TO LET MAVEN TREAT IT LIKE A CONTROLLER
@Controller
@SessionAttributes("userID")
public class OfficerHomeController {

	@RequestMapping("/officerHome")
	public ModelAndView officerHome(ModelMap model) {
		return new ModelAndView("officerhome");
	}

	@RequestMapping("/operatorHome")
	public ModelAndView operatorHome(ModelMap model) {
		return new ModelAndView("operatorhome");
	}
}
