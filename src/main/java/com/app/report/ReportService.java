package com.app.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	public List<Report> getAllReports(){
		List<Report> reports = new ArrayList<>();
		reportRepository.findAll().forEach(reports::add); //query all reports in DB, and add each of them into reports
		return reports;
	}
	
	public List<Report> getAllOperatorReports(int userID){
		List<Report> reports = new ArrayList<>();
		reportRepository.findByOperatorUserID(userID).forEach(reports::add); //query all reports based on operatorId
		return reports;
	}

	public Report getReport(int reportID){
		return reportRepository.findOne(reportID);
	}
	
	public void addReport(Report operator){
		reportRepository.save(operator);
	}
	
	public void updateReport(int reportID, Report operator){
		reportRepository.save(operator); //repository smart enough to find tuple with stated id
	}

	public void deleteReport(String id){
		reportRepository.delete(id);
	}
}
