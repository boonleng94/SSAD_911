package com.app.registry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {

	@Autowired
	private RegistryRepository registryRepository;
	
	public List<Registry> getAllRegistry(){
		List<Registry> registry = new ArrayList<>();
		registryRepository.findAll().forEach(registry::add); //query all reports in DB, and add each of them into reports
		return registry;
	}

	public Registry getRegistry(String nric){
		return registryRepository.findFirstByNric(nric);
	}
	
	public void addRegistry(Registry registry){
		registryRepository.save(registry);
	}
	
	public void updateRegistry(String nric, Registry registry){
		registryRepository.save(registry); //repository smart enough to find tuple with stated id
	}

	public void deleteReport(String id){
		registryRepository.delete(id);
	}
}
