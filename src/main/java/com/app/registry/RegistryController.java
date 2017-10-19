package com.app.registry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {
	
	@Autowired
	private RegistryService registryService;
	
	public List<Registry> getAllReports(){
		return registryService.getAllRegistry();
	}
	
	//curly braces for inputs
	public Registry getRegistry(@PathVariable String nric){
		return registryService.getRegistry(nric);
	}
	
	//method to save registry
	public void addRegistry(@RequestBody Registry report, @PathVariable String nric){
		registryService.addRegistry(report);
	}
	
	public void updateRegistry(@RequestBody Registry report, @PathVariable String nric){
		registryService.updateRegistry(nric, report);
	}

	public void deleteReport(@PathVariable String nric){
		registryService.deleteReport(nric);
	}
}