package com.app.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.User;

@RestController
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/report")
	public List<Report> getAllReports(){
		return reportService.getAllReports();
	}

	@RequestMapping("/operator/{id}/report")
	public List<Report> getAllOperatorReports(@PathVariable int userID){
		return reportService.getAllOperatorReports(userID);
	}
	
	//curly braces for inputs
	@RequestMapping("/report/{id}")
	public Report getReport(@PathVariable int reportID){ //need use @pathvariable. convention to keep names same
		return reportService.getReport(reportID);
	}
	
	//@RequestMapping("/operator/{operatorId}/report/{id}")
	//public Report getOperatorReport(@PathVariable String id){ //need use @pathvariable. convention to keep names same
	//	return reportService.getReport(id);
	//}
	
	@RequestMapping(method=RequestMethod.POST, value="/operator/{operatorId}/report")
	public void addReport(@RequestBody Report report, @PathVariable int userID){
		report.setOperator(new User(userID,"","", null, false));
		reportService.addReport(report);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/operator/{operatorId}/report/{id}")
	public void updateReport(@RequestBody Report report, @PathVariable int userID, @PathVariable int reportID){
		report.setOperator(new User(userID,"","", null, false));
		reportService.updateReport(reportID, report);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/operator/{operatorId}/report/{id}")
	public void deleteReport(@PathVariable String id){
		reportService.deleteReport(id);
	}
}