package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userID")
public class WarningPageController {
	
	@RequestMapping("/warning")
	public ModelAndView errorPage(ModelMap model) {
		return new ModelAndView("warning");
	}
}
