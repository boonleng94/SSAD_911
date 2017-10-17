package com.app.controller;

<<<<<<< HEAD:src/main/java/com/app/controller/HashController.java
<<<<<<< HEAD:src/main/java/com/app/controller/HashController.java
import java.security.MessageDigest;

public class HashController {
	// generate hash for the password using SHA-256
	public static String hash(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
=======
=======
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/controllers/LoginController.java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
<<<<<<< HEAD:src/main/java/com/app/controller/HashController.java
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/controllers/LoginController.java
=======
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/controllers/LoginController.java
	}
}
