package com.javainuse.operator;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OperatorRepository extends CrudRepository<Operator, String>{
	 
	@Modifying
	@Query("delete from Operator where username = :username")
	void delete(@Param("username") int id);
}
