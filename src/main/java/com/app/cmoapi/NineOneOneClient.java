package com.app.cmoapi;
/*//Client side
//Specify their URI
//Create a method like createReport() to POST to their service
package cmoapi;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.javainuse.report.CallReport;

public class NineOneOneClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/911toCMO";

	// GET
	@SuppressWarnings({ "unchecked"})
	private static void listLatestCallReport() {
		int i = 1;
		System.out.println("Testing receiving Call Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> callReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/",
				List.class);

		if (callReportsMap != null) {
			for (LinkedHashMap<String, Object> map : callReportsMap) {
				if (i++ == callReportsMap.size()) {
					// end
					
					System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
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
	private static void listAllCallReports() {
		System.out.println("Testing receiving Call Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> callReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/",
				List.class);

		if (callReportsMap != null) {
			for (LinkedHashMap<String, Object> map : callReportsMap) {

				System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
						+ " positionIn911=" + map.get("positionIn911") + "," + " crisisType=" + map.get("crisisType") + ","
						+ " affectedArea=" + map.get("affectedArea") + "," + " crisisDate=" + map.get("crisisDate") + ","
						+ " estimatedStartTime=" + map.get("estimatedStartTime") + ","
						+ " crisisDetails=" + map.get("crisisDetails"));
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	private static void getCallReport() {
		System.out.println("Testing get Call Report API----------");

		RestTemplate restTemplate = new RestTemplate();
		CallReport callReport = restTemplate.getForObject(REST_SERVICE_URI + "/callReport/1", CallReport.class);
		System.out.println(callReport);
	}

	// POST 
	private static void createCallReport() {
	System.out.println("Testing create Call Report API----------");

	RestTemplate restTemplate = new RestTemplate();
	CallReport callReport = new CallReport(10, "Test10", "911 Liaison Officer" , "Type10", "Area10", "Date10", "Time10", "Detail10");
	URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/callReport/", callReport,
			CallReport.class);System.out.println("Location : "+uri.toASCIIString());
	}

	public static void main(String args[]) {
		createCallReport();
		getCallReport();
		listAllCallReports();
		listLatestCallReport();
	}
}*/