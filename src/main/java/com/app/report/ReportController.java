<<<<<<< HEAD:src/main/java/com/app/report/ReportController.java
<<<<<<< HEAD:src/main/java/com/app/report/ReportController.java
package com.app.report;
=======
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/report/ReportController.java
=======
>>>>>>> 7fe5a0048e227664dc0ee5c8dbe9dfd06a920fc2:src/main/java/com/javainuse/report/ReportController.java
/*package com.javainuse.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.operator.*;

@RestController
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/report")
	public List<Report> getAllReports(){
		return reportService.getAllReports();
	}

	@RequestMapping("/operator/{id}/report")
	public List<Report> getAllOperatorReports(@PathVariable String id){
		return reportService.getAllOperatorReports(id);
	}
	
	//curly braces for inputs
	@RequestMapping("/report/{id}")
	public Report getReport(@PathVariable String id){ //need use @pathvariable. convention to keep names same
		return reportService.getReport(id);
	}
	
	@RequestMapping("/operator/{operatorId}/report/{id}")
	public Report getOperatorReport(@PathVariable String id){ //need use @pathvariable. convention to keep names same
		return reportService.getReport(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/operator/{operatorId}/report")
	public void addReport(@RequestBody Report report, @PathVariable String operatorId){
		report.setOperator(new Operator(operatorId,"",""));
		reportService.addReport(report);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/operator/{operatorId}/report/{id}")
	public void updateReport(@RequestBody Report report, @PathVariable String operatorId, @PathVariable String id){
		report.setOperator(new Operator(operatorId,"",""));
		reportService.updateReport(id, report);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/operator/{operatorId}/report/{id}")
	public void deleteReport(@PathVariable String id){
		reportService.deleteReport(id);
	}
}*/