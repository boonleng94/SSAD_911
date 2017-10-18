package com.app.report;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.user.User;

@Entity
@Table(name = "reports", schema = "911")
public class Report {

	@Id
	private int reportID;
	private Date date;
	private Time callStartTime;
	private Time callEndTime;
	
	private String callerName;
	private String callerNric;
	private String callerLocation;
	
	private String incidentNature;
	private Time estimatedStartTime;
	private Time estimatedEndTime;
	private int noOfCasualties;
	private String incidentLocation;
	private String additionalNotes;
	private String incidentCategory;
	
	private int operatorUserID;
	private String versionId;
	private String changelog;
	
//	@ManyToOne
//	private User operator;

	public Report() {
		
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getCallStartTime() {
		return callStartTime;
	}

	public void setCallStartTime(Time callStartTime) {
		this.callStartTime = callStartTime;
	}

	public Time getCallEndTime() {
		return callEndTime;
	}

	public void setCallEndTime(Time calLEndTime) {
		this.callEndTime = calLEndTime;
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

	public void setCallerNric(String callerNRIC) {
		this.callerNric = callerNRIC;
	}

	public String getCallerLocation() {
		return callerLocation;
	}

	public void setCallerLocation(String callerLocation) {
		this.callerLocation = callerLocation;
	}

	public String getIncidentNature() {
		return incidentNature;
	}

	public void setIncidentNature(String incidentNature) {
		this.incidentNature = incidentNature;
	}

	public Time getEstimatedStartTime() {
		return estimatedStartTime;
	}

	public void setEstimatedStartTime(Time estimatedStartTime) {
		this.estimatedStartTime = estimatedStartTime;
	}

	public Time getEstimatedEndTime() {
		return estimatedEndTime;
	}

	public void setEstimatedEndTime(Time estimatedEndTime) {
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

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public String getIncidentCategory() {
		return incidentCategory;
	}

	public void setIncidentCategory(String incidentCategory) {
		this.incidentCategory = incidentCategory;
	}

	public int getoperatorUserID() {
		return operatorUserID;
	}

	public void setoperatorUserID(int operatorUserID) {
		this.operatorUserID = operatorUserID;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getChangelog() {
		return changelog;
	}

	public void setChangelog(String changelog) {
		this.changelog = changelog;
	}

//	public User getOperator() {
//		return operator;
//	}
//
//	public void setOperator(User operator) {
//		this.operator = operator;
//	}
}