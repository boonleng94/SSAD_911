package com.app.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private static UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add); //query all operators in DB, and add each of them into operators
		return users;
	}

	public User getUser(int id){
		List<User> users = getAllUsers();
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getUserID() == id){
				return users.get(i);
			}
		}
		return null;
	}
	
	public void addUser(User user){
		userRepository.save(user);
	}
	
	public void updateUser(int id, User user){
		userRepository.save(user); //repository smart enough to find tuple with stated id
	}

	public void deleteUser(String id){
		userRepository.delete(id);
	}
	
	public User getUserByLogin(String username, String password){
		//return userRepository.findFirstByUsernameAndPassword(username, password);
		return userRepository.findFirstByUsernameAndPassword(username, password);
	}
}
