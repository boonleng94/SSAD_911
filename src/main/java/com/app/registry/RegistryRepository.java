package com.app.registry;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegistryRepository extends CrudRepository<Registry, String>{
	
	//creating a custom .findBy function that finds reports based on operatorId // SYNTAX IS: findByEntityAttribute
	public List<Registry> findByName(String name);

	public List<Registry> findByAddress(String address);

	public List<Registry> findByDob(String dob);

	@Modifying
	@Query("from Registry where nric = :nric")
	public Registry findOne(String nric);
		
	@Modifying
	@Query("delete from Registry where nric = :nric")
	void delete(@Param("nric") int nric);

}