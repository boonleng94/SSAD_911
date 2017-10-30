package com.app.registry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {
	
	@Autowired
	private RegistryService registryService;
	
	@RequestMapping("/registry")
	public List<Registry> getAllReports(){
		return registryService.getAllRegistry();
	}
	
	@RequestMapping("/registry/{id}")
	public Registry getRegistry(@PathVariable String nric){ //need use @pathvariable. convention to keep names same
		return registryService.getRegistry(nric);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/registry")
	public void addRegistry(@RequestBody Registry report, @PathVariable String nric){
		registryService.addRegistry(report);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/registry/{nric}")
	public void updateRegistry(@RequestBody Registry report, @PathVariable String nric){
		registryService.updateRegistry(nric, report);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/registry/{nric}")
	public void deleteReport(@PathVariable String nric){
		registryService.deleteReport(nric);
	}

	public Registry getRegistryToVerify(String nric){ //need use @pathvariable. convention to keep names same
		return registryService.getRegistry(nric);
	}
}