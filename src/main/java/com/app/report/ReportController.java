package com.app.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	public List<Report> getAllReports(){
		return reportService.getAllReports();
	}

	//get all reports from 1 operator via ID
	public List<Report> getAllOperatorReports(int userID){
		return reportService.getAllOperatorReports(userID);
	}
	
	public Report getReport(int reportID){
		return reportService.getReport(reportID);
	}
	
	//method to save report
	public void addReport(Report report, int userID){
		report.setoperatorUserID(userID);
		reportService.addReport(report);
	}
	
	public void updateReport(Report report, int userID, int reportID){
		report.setoperatorUserID(userID);
		reportService.updateReport(reportID, report);
	}

	public void deleteReport(String reportID){
		reportService.deleteReport(reportID);
	}
}