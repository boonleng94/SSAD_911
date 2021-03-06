package com.app.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/report")
	public List<Report> getAllReports(){
		return reportService.getAllReports();
	}

	//get all reports not sent by LO via ID
	public List<Report> getAllOperatorReports(){
		return reportService.getAllOperatorReports();
	}
	
	//curly braces for inputs
	@RequestMapping("/report/{id}")
	public Report getReport(@PathVariable int reportID){ //need use @pathvariable. convention to keep names same
		return reportService.getReport(reportID);
	}
	
	//method to save report
	@RequestMapping(method=RequestMethod.POST, value="/operator/{operatorId}/report")
	public void addReport(@RequestBody Report report, @PathVariable int userID){
		report.setOperatorUserID(userID);
		reportService.addReport(report);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/operator/{userID}/report/{reportID}")
	public void updateReport(@RequestBody Report report, @PathVariable int userID, @PathVariable int reportID){
		report.setOperatorUserID(userID);
		Report savedReport = reportService.updateReport(reportID, report);
	}
	
	public Report updateReportOfficer(Report report, int officerID, int reportID){
		return reportService.updateReport(reportID, report);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/operator/{operatorId}/report/{reportID}")
	public void deleteReport(@PathVariable String reportID){
		reportService.deleteReport(reportID);
	}
	
	public List<Report> getAllReportsforLO(){
		return reportService.getReportsByCategoryAndNotStatus("CAT1", "Drafted");
	}

	public List<Integer> getAllCrisisIDs(){
		return reportService.getAllCrisisIDs();
	}
}