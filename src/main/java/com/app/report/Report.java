<<<<<<< HEAD:src/main/java/com/app/report/Report.java
package com.app.report;
/*package report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import operator.*;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String userId;
	private String password;
	private String name;
	
	@ManyToOne
	private Operator operator;

	public Report() {
		
	}
	
	public Report(String userId, String password, String name, String operatorId) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.operator = new Operator(operatorId, "", "");
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
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
*/
=======
//package com.javainuse.report;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//import com.javainuse.operator.*;
//
//@Entity
//public class Report {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private String userId;
//	private String password;
//	private String name;
//	
//	@ManyToOne
//	private Operator operator;
//
//	public Report() {
//		
//	}
//	
//	public Report(String userId, String password, String name, int operatorId) {
//		super();
//		this.userId = userId;
//		this.password = password;
//		this.name = name;
//		this.operator = new Operator(operatorId, "", "");
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public Operator getOperator() {
//		return operator;
//	}
//
//	public void setOperator(Operator operator) {
//		this.operator = operator;
//	}
//}
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/report/Report.java
