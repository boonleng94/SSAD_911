package com.app.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "911")
public class User {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	private String username; 
	private String password;
	private String name;
	private boolean liaisonOfficer;
	
	public User() {
		
	}
	
	public User(int userID, String username, String password, String name, boolean liaisonOfficer) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.name = name;
		this.liaisonOfficer = liaisonOfficer;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int operatorID) {
		this.userID = operatorID;
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

	public boolean getliaisonOfficer() {
		return liaisonOfficer;
	}

	public void setliaisonOfficer(boolean liaisonOfficer) {
		this.liaisonOfficer = liaisonOfficer;
	}
	@Override
	public String toString() {
		return "User [operatorID=" + userID + ", username=" + username + ", password=" + password + ", name=" + name + ", liaisonOfficer=" + liaisonOfficer + "]";
	}
}
