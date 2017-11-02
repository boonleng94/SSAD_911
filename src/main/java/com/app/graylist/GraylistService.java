package com.app.graylist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraylistService {

	@Autowired
	private GraylistRepository graylistRepository;
	
	public List<Graylist> getAllGraylist(){
		List<Graylist> graylist = new ArrayList<>();
		graylistRepository.findAll().forEach(graylist::add); //query all reports in DB, and add each of them into reports
		return graylist;
	}

//	public long getCallerNricCount(int callerNric){
//		return graylistRepository.countByCallerNric(callerNric);
//	}
	
	public void addGraylist(Graylist graylist){
		graylistRepository.save(graylist);
	}
}
