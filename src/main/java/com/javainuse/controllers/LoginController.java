package com.javainuse.controllers;

import java.security.MessageDigest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
	}
<<<<<<< HEAD:src/main/java/com/javainuse/controllers/LoginController.java

	// generate hash for the password using SHA-256
	public String hash(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
=======
}
>>>>>>> 155d85f1b86ac0c2334a86117fa9c78f88f1e2e5:src/main/java/app/LoginController.java
