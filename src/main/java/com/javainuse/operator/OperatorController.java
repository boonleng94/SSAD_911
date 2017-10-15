package com.javainuse.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {
	
	@Autowired
	private OperatorService operatorService;
	
	@RequestMapping("/operator")
	public List<Operator> returnAllOperator(){
		return operatorService.getAllOperators();
	}
	
	//curly braces for inputs
	@RequestMapping("/operator/{id}")
	public Operator getOperator(@PathVariable String id){ //need use @pathvariable. convention to keep names same
		return operatorService.getOperator(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/operator")
	public void addOperator(@RequestBody Operator operator){
		operatorService.addOperator(operator);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/operator/{id}")
	public void updateOperator(@RequestBody Operator operator, @PathVariable String id){
		operatorService.updateOperator(id, operator);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/operator/{id}")
	public void deleteOperator(@PathVariable String id){
		operatorService.deleteOperator(id);
	}
}