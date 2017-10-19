package com.app.report;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends CrudRepository<Report, String>{
	
	//creating a custom .findBy function that finds reports based on operatorId // SYNTAX IS: findByEntityAttribute
	public List<Report> findByOperatorUserID(int userID);

	@Modifying
	@Query("from Report where reportID = :reportID")
	public Report findOne(int reportID);
		
	@Modifying
	@Query("delete from Report where reportID = :reportID")
	void delete(@Param("reportID") int reportID);

}