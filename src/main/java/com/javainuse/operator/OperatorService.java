package com.javainuse.operator;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.TomcatPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;
	
	public List<Operator> getAllOperators(){
		List<Operator> operators = new ArrayList<>();
		operatorRepository.findAll().forEach(operators::add); //query all operators in DB, and add each of them into operators
		return operators;
	}

	public Operator getOperator(String id){
		return operatorRepository.findOne(id);
	}
	
	public void addOperator(Operator operator){
		operatorRepository.save(operator);
	}
	
	public void updateOperator(String id, Operator operator){
		operatorRepository.save(operator); //repository smart enough to find tuple with stated id
	}

	public void deleteOperator(String id){
		operatorRepository.delete(id);
	}
	
	// generate hash for the password using SHA-256
	public String hash(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
