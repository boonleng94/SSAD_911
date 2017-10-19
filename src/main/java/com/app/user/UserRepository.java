package com.app.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String>{
	 
	@Modifying
	@Query("delete from User where username = :username")
	public void delete(@Param("username") String username);
	
	public User findFirstByUsernameAndPassword(String username, String password);

	public User findFirstByUserID(int userID);
}
