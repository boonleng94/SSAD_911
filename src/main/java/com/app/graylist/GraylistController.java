package com.app.graylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraylistController {
	
	@Autowired
	private GraylistService graylistService;
		
	@RequestMapping("/graylist")
	public List<Graylist> getAllGraylist(){
		return graylistService.getAllGraylist();
	}
	
	@RequestMapping("/graylist/{callerNumber}")
	public long getCallNumberCount(@PathVariable int callerNumber){ //need use @pathvariable. convention to keep names same
		return graylistService.getCallerNumberCount(callerNumber);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/graylist")
	public void addgraylist(@RequestBody Graylist graylist){
		graylistService.addGraylist(graylist);
	}
}