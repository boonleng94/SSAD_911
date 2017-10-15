package com.javainuse.operator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operator {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String userId;
	private String password;
	private String name;
	
	public Operator() {
		
	}
	
	public Operator(String userId, String password, String name) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return "Operator [userId=" + userId + ", password=" + password + ", name=" + name + "]";
	}
}
