package com.app.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	 
	@Modifying
	@Query("delete from User where username = :username")
	public void delete(@Param("username") String username);
	
	//@Query(value = "SELECT * FROM user WHERE username = /"?0/" AND password = ?1", nativeQuery=true)
//	@Query("select from User where username = :username and password = :password")
//	public User getUserByLogin(@Param("username") String username, @Param("password") String password);
	
	public User findFirstByUsernameAndPassword(String username, String password);
}
