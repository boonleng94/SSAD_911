//Client side
//Specify their URI
//Create a method like createReport() to POST to their service
package com.app.cmoapi;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.app.cmoapi.CallReport;
import com.app.report.Report;
import com.app.user.User;

@Controller
public class NineOneOneClient {

	//public static final String REST_SERVICE_URI = "http://10.27.114.25:8080/911toCMO";
	public static final String REST_SERVICE_URI = "http://localhost:8080/911toCMO";
	
	// GET
	@SuppressWarnings({ "unchecked"})
	public static void listLatestCallReport() {
		int i = 1;
		System.out.println("Testing receiving Call Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> callReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/",
				List.class);

		if (callReportsMap != null) {
			for (LinkedHashMap<String, Object> map : callReportsMap) {
				if (i++ == callReportsMap.size()) {
					// end
					
					System.out.println("callReportID=" + map.get("callReportID") + "crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
							+ " positionIn911=" + map.get("positionIn911") + "," + " crisisType=" + map.get("crisisType") + ","
							+ " affectedArea=" + map.get("affectedArea") + "," + " crisisDate=" + map.get("crisisDate") + ","
							+ " estimatedStartTime=" + map.get("estimatedStartTime") + ","
							+ " crisisDetails=" + map.get("crisisDetails"));
				}
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	@SuppressWarnings({"unchecked" })
	public static void listAllCallReports() {
		System.out.println("Testing receiving Call Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> callReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/",
				List.class);

		if (callReportsMap != null) {
			for (LinkedHashMap<String, Object> map : callReportsMap) {

				System.out.println("callReportID=" + map.get("callReportID") + "crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
						+ " positionIn911=" + map.get("positionIn911") + "," + " crisisType=" + map.get("crisisType") + ","
						+ " affectedArea=" + map.get("affectedArea") + "," + " crisisDate=" + map.get("crisisDate") + ","
						+ " estimatedStartTime=" + map.get("estimatedStartTime") + ","
						+ " crisisDetails=" + map.get("crisisDetails"));
			}
		} else {
			System.out.println("No report exists----------");
		}
	}

	// GET
	public static void getCallReport() {
		System.out.println("Testing get Call Report API----------");

		RestTemplate restTemplate = new RestTemplate();
		CallReport callReport = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/1", CallReport.class);
		System.out.println(callReport);
	}

	// POST 
	public static void createCallReport(Report report, User user) {
		System.out.println("Testing create Call Report API----------");
	
		RestTemplate restTemplate = new RestTemplate();
		
		CallReport callReport = new CallReport(report.getReportID(), report.getCrisisID(), user.getName(), 
				((user.getLiaisonOfficer() == true) ? "CMO Liaison Officer. "  : "Non-CMO Liaison Officer") , report.getIncidentNature() , report.getIncidentLocation() + 
				"/" + report.getIncidentCoord_n() + "/" + report.getIncidentCoord_e(), report.getIncidentDate(), report.getEstimatedStartTime(), report.getAdditionalNotes());
		callReport.setCallReportID(444);
		
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/callReport/", callReport, CallReport.class);
		System.out.println("Location : "+uri.toASCIIString());
	}
}