package com.app.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports", schema = "911")
public class Report {

	@Id
	private int reportID;
	private String date;
	private String callStartTime;
	private String callEndTime;
	private String callerLocation;
	private String callCoord_n;
	private String callCoord_e;
	
	private String callerName;
	private String callerNric;
	private String dob;
	private boolean callerVerified;
	private String authenticity;
	private String reason;

	private String incidentCategory;
	private String incidentNature;
	private String incidentDate;
	private String estimatedStartTime;
	private String estimatedEndTime;
	private int noOfCasualties;
	private String incidentLocation;
	private String incidentCoord_n;
	private String incidentCoord_e;
	private String additionalNotes;
	
	private int operatorUserID;
	private String status;
	private int officerUserID;
	private int crisisID;
	private String dateTimeModified;

	public Report() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();		
		this.dateTimeModified = dateFormat.format(currentDate);
	}
    
	public Report(int reportID, String date, String callStartTime, String callEndTime, String callerLocation,
			String callCoord_n, String callCoord_e, String callerName, String callerNric, String dob,
			boolean callerVerified, String authenticity, String reason, String incidentCategory, String incidentNature,
			String incidentDate, String estimatedStartTime, String estimatedEndTime, int noOfCasualties,
			String incidentLocation, String incidentCoord_n, String incidentCoord_e, String additionalNotes,
			int operatorUserID, String status, int officerUserID, int crisisID) {
		super();
		this.reportID = reportID;
		this.date = date;
		this.callStartTime = callStartTime;
		this.callEndTime = callEndTime;
		this.callerLocation = callerLocation;
		this.callCoord_n = callCoord_n;
		this.callCoord_e = callCoord_e;
		this.callerName = callerName;
		this.callerNric = callerNric;
		this.dob = dob;
		this.callerVerified = callerVerified;
		this.authenticity = authenticity;
		this.reason = reason;
		this.incidentCategory = incidentCategory;
		this.incidentNature = incidentNature;
		this.incidentDate = incidentDate;
		this.estimatedStartTime = estimatedStartTime;
		this.estimatedEndTime = estimatedEndTime;
		this.noOfCasualties = noOfCasualties;
		this.incidentLocation = incidentLocation;
		this.incidentCoord_n = incidentCoord_n;
		this.incidentCoord_e = incidentCoord_e;
		this.additionalNotes = additionalNotes;
		this.operatorUserID = operatorUserID;
		this.status = status;
		this.officerUserID = officerUserID;
		this.crisisID = crisisID;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();		
		this.dateTimeModified = dateFormat.format(currentDate);
	}
	
	
	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCallStartTime() {
		return callStartTime;
	}

	public void setCallStartTime(String callStartTime) {
		this.callStartTime = callStartTime;
	}

	public String getCallEndTime() {
		return callEndTime;
	}

	public void setCallEndTime(String callEndTime) {
		this.callEndTime = callEndTime;
	}

	public String getCallerLocation() {
		return callerLocation;
	}

	public void setCallerLocation(String callerLocation) {
		this.callerLocation = callerLocation;
	}

	public String getCallCoord_n() {
		return callCoord_n;
	}

	public void setCallCoord_n(String callCoord_n) {
		this.callCoord_n = callCoord_n;
	}

	public String getCallCoord_e() {
		return callCoord_e;
	}

	public void setCallCoord_e(String callCoord_e) {
		this.callCoord_e = callCoord_e;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getCallerNric() {
		return callerNric;
	}

	public void setCallerNric(String callerNric) {
		this.callerNric = callerNric;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean isCallerVerified() {
		return callerVerified;
	}

	public void setCallerVerified(boolean callerVerified) {
		this.callerVerified = callerVerified;
	}

	public String getAuthenticity() {
		return authenticity;
	}

	public void setAuthenticity(String authenticity) {
		this.authenticity = authenticity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIncidentCategory() {
		return incidentCategory;
	}

	public void setIncidentCategory(String incidentCategory) {
		this.incidentCategory = incidentCategory;
	}

	public String getIncidentNature() {
		return incidentNature;
	}

	public void setIncidentNature(String incidentNature) {
		this.incidentNature = incidentNature;
	}

	public String getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getEstimatedStartTime() {
		return estimatedStartTime;
	}

	public void setEstimatedStartTime(String estimatedStartTime) {
		this.estimatedStartTime = estimatedStartTime;
	}

	public String getEstimatedEndTime() {
		return estimatedEndTime;
	}

	public void setEstimatedEndTime(String estimatedEndTime) {
		this.estimatedEndTime = estimatedEndTime;
	}

	public int getNoOfCasualties() {
		return noOfCasualties;
	}

	public void setNoOfCasualties(int noOfCasualties) {
		this.noOfCasualties = noOfCasualties;
	}

	public String getIncidentLocation() {
		return incidentLocation;
	}

	public void setIncidentLocation(String incidentLocation) {
		this.incidentLocation = incidentLocation;
	}

	public String getIncidentCoord_n() {
		return incidentCoord_n;
	}

	public void setIncidentCoord_n(String incidentCoord_n) {
		this.incidentCoord_n = incidentCoord_n;
	}

	public String getIncidentCoord_e() {
		return incidentCoord_e;
	}

	public void setIncidentCoord_e(String incidentCoord_e) {
		this.incidentCoord_e = incidentCoord_e;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public int getOperatorUserID() {
		return operatorUserID;
	}

	public void setOperatorUserID(int operatorUserID) {
		this.operatorUserID = operatorUserID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOfficerUserID() {
		return officerUserID;
	}

	public void setOfficerUserID(int officerUserID) {
		this.officerUserID = officerUserID;
	}

	public int getCrisisID() {
		return crisisID;
	}

	public void setCrisisID(int crisisID) {
		this.crisisID = crisisID;
	}


	public String getDateTimeModified() {
		return dateTimeModified;
	}

	public void setDateTimeModified(String dateTimeModified) {
		this.dateTimeModified = dateTimeModified;
	}
}