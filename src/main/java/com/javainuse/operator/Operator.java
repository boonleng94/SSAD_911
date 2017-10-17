package com.javainuse.operator;

import java.security.MessageDigest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operator {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int operatorID;
	private String username; 
	private String password;
	private String name;
	
	public Operator() {
		
	}
	
	public Operator(int operatorID, String password, String name) {
		super();
		this.operatorID = operatorID;
		this.password = password;
		this.name = name;
	}
	
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

	public int getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Operator [operatorID=" + operatorID + ", username=" + username + ", password=" + password + ", name=" + name + "]";
	}
}
