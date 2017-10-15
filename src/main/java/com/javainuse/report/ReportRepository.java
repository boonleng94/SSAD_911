package com.javainuse.report;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, String>{
	
	//creating a custom .findBy function that finds reports based on operatorId // SYNTAX IS: findByEntityAttribute
	public List<Report> findByOperatorUserId(String operatorId);
}