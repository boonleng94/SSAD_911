package com.app.graylist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "graylist", schema = "911")
public class Graylist {
	
	@Id
	private int graylistID;
	private String callerNric;
	private String reason;

	public Graylist() {
	}
	
	public Graylist(int graylistID, String callerNric, String reason) {
		super();
		this.graylistID = graylistID;
		this.callerNric = callerNric;
		this.reason = reason;
	}

	public int getGraylistID() {
		return graylistID;
	}

	public void setGraylistID(int graylistID) {
		this.graylistID = graylistID;
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
		return "Graylist [graylistID=" + graylistID + ", callerNric=" + callerNric + ", reason=" + reason + "]";
	}
	
}