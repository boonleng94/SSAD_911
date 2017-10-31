package com.app.graylist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "graylist", schema = "911")
public class Graylist {
	
	@Id
	private int graylistID;
	private int callerNumber;
	private String callerNric;
	private String reason;

	public Graylist() {
	}
	
	public Graylist(int graylistID, int callerNumber, String callerNric, String reason) {
		super();
		this.graylistID = graylistID;
		this.callerNumber = callerNumber;
		this.callerNric = callerNric;
		this.reason = reason;
	}

	public int getGraylistID() {
		return graylistID;
	}

	public void setGraylistID(int graylistID) {
		this.graylistID = graylistID;
	}

	public int getCallerNumber() {
		return callerNumber;
	}
	public void setCallerNumber(int callerNumber) {
		this.callerNumber = callerNumber;
	}
	public String getCallerNric() {
		return callerNric;
	}
	public void setCallerNric(String callerNric) {
		this.callerNric = callerNric;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Graylist [graylistID=" + graylistID + ", callerNumber=" + callerNumber + ", callerNric=" + callerNric+ ", reason=" + reason + "]";
	}
	
}