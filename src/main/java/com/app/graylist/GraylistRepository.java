package com.app.graylist;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GraylistRepository extends CrudRepository<Graylist, String>{
	
	//creating a custom .findBy function that finds reports based on operatorId // SYNTAX IS: findByEntityAttribute
//	public List<Graylist> findByCallerNric(int callerNric);
//
//	public long countByCallerNric(int callerNric);

	/*@Modifying
	@Query("from Graylist where callerNumber = :callerNumber")
	public Graylist findOne(String nric);
		
	@Modifying
	@Query("delete from Registry where nric = :nric")
	void delete(@Param("nric") int nric);
	
	public Graylist findFirstByNric(String nric);*/
}