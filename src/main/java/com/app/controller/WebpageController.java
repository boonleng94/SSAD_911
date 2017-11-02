package com.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.cmoapi.NineOneOneClient;
import com.app.graylist.Graylist;
import com.app.graylist.GraylistController;
import com.app.registry.Registry;
import com.app.registry.RegistryController;
import com.app.report.Report;
import com.app.report.ReportController;
import com.app.user.User;
import com.app.user.UserController;

@Controller
@SessionAttributes("userID")
public class WebpageController implements ErrorController{
	
	@Autowired
	UserController userController;
	@Autowired 
	ReportController reportController;
	@Autowired 
	RegistryController registryController;	
	@Autowired 
	GraylistController graylistController;
	
	User user = new User();
	private static final String ERRORPATH = "/error";
	
	/**
	 * Mapping for main page
	 * If user is logged in, redirect to home page
	 * Else, redirect to login page
	 */
	@RequestMapping("/")
	public ModelAndView mainPage(ModelMap model) {
		int userInSession = 0;
		
		if(model.get("userID") != null) {
			userInSession = (int) model.get("userID");
		}

		if(userInSession == 0)
			return new ModelAndView("login");
		else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	/**
	 * Mapping for home page
	 * If user is a liaison officer, redirect to officer home page
	 * Else, redirect to operator home page
	 */
	@RequestMapping("/home")
	public ModelAndView homePage(ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		} 
		else {
			model.put("name", user.getName());
			if (userController.isLiaisonOfficer((int) model.get("userID"))){

				List<Report> reports = reportController.getAllReportsforLO();
				model.put("operatorList", userController.getAllOperators());
				model.put("reportList", reports);
				
				return new ModelAndView("officerhome");
			}
			else{
				List<Report> reports = reportController.getAllOperatorReports((int) model.get("userID"));
				model.put("OpsReportList", reports);
				return new ModelAndView("operatorhome");
			}
		}
	}
	
	/**
	 * Mapping for new report page
	 * Used by operator for creating a new emergency report upon receiving 911 call
	 * And inserting into the database
	 * ZH: ONLY OPERATOR CAN CREATE REPORT
	 */
	@RequestMapping(value="/newReport")
	public ModelAndView reportPage(ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		
		if (userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "You are not authorized. Only operators are allowed to create new reports.");
			model.put("redirect", "/home");
			return new ModelAndView("/message");
		}
		else {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			model.put("userID", (int) model.get("userID"));
			return new ModelAndView("newreport");
		}
	}
	
	/**
	 * Mapping for displaying selected report page
	 * Used for displaying selected reports to edit / authenticate
	 */
	@RequestMapping(value ="/editReport", method=RequestMethod.POST)
	public ModelAndView editReportPage(@RequestParam int reportID, ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		
		if (userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("name", user.getName());
			model.put("report", reportController.getReport(Integer.valueOf(reportID)));
			
			List<Integer> ints = reportController.getAllCrisisIDs();
			Collections.sort(ints);
			model.put("crisisIDs", ints);

			return new ModelAndView("officereditreport");
		}
		else {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			
			Report report = reportController.getReport(Integer.valueOf(reportID));
			model.put("report", report);

			
			List<Graylist> allGraylist = graylistController.getAllGraylist();
			for(int i = 0; i < allGraylist.size(); i++){
				if(allGraylist.get(i).getCallerNric().equals(report.getCallerNric())){
					model.put("graylisted", true);
					break;
				}else if(i == allGraylist.size() - 1){
					model.put("graylisted", false);
				}
			}
			
			return new ModelAndView("operatoredit");
		}
	}
	
	/**
	 * Mapping for saving POSTed report to update
	 */
	@RequestMapping(value ="/officerUpdateReport", method=RequestMethod.POST)
	public ModelAndView updateReportPage(@RequestParam String reportID, @RequestParam String authenticity, @RequestParam String reason, @RequestParam String incidentCategory, 
			@RequestParam String incidentNature, @RequestParam String incidentLocation, @RequestParam String incidentCoord_n, @RequestParam String incidentCoord_e, 
			@RequestParam String crisisID, @RequestParam String additionalNotes, @RequestParam String action, ModelMap model) {
		
		if(model.get("userID") == null || (int) model.get("userID") == 0 || !userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "Only Liaison Officers can access this page.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			model.put("report", reportController.getReport(Integer.valueOf(reportID)));
			
			Report updatedReport = reportController.getReport(Integer.valueOf(reportID));
			updatedReport.setIncidentCategory(incidentCategory);
			updatedReport.setIncidentNature(incidentNature);
			updatedReport.setIncidentLocation(incidentLocation);
			updatedReport.setIncidentCoord_n(incidentCoord_n);
			updatedReport.setIncidentCoord_e(incidentCoord_e);
			updatedReport.setCrisisID(Integer.parseInt(crisisID));
			updatedReport.setAdditionalNotes(additionalNotes);
			updatedReport.setAuthenticity(authenticity);
			updatedReport.setReason(reason);
			
			if(action.equals("submit")){
				try {
					User user = userController.getUserToSubmitReport(updatedReport.getOfficerUserID());
					NineOneOneClient.createCallReport(updatedReport, user);
					updatedReport.setStatus("Sent");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("can't submit");
				}
			}
			Report savedReport = reportController.updateReportOfficer(updatedReport, (int) model.get("userID"), Integer.parseInt(reportID));
			
			return new ModelAndView("redirect:/home");
		}
	}
	
	/**
	 * Mapping for new report page
	 * Used by operator for creating a new emergency report upon receiving 911 call
	 * And inserting into the database
	 * ZH: ONLY OPERATOR CAN CREATE REPORT
	 */
	@RequestMapping(value="/livefeed")
	public ModelAndView liveFeedPage(ModelMap model) {
		if(model.get("userID") == null || (int) model.get("userID") == 0) {
			model.put("message", "Please log in first.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		
		if (userController.isLiaisonOfficer((int) model.get("userID"))) {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			return new ModelAndView("livefeed");
		}
		else {
			model.put("message", "You are not authorized to view this page.");
			model.put("redirect", "/home");
			return new ModelAndView("/message");
		}
	}

	/**
	 * Mapping for error page
	 */
	@RequestMapping(ERRORPATH)
	public ModelAndView errorPage(ModelMap model) {
		model.put("message", "This page is not available.");
		model.put("redirect", "/");
		return new ModelAndView("message");
	}
	
	@Override
	public String getErrorPath() {
		return ERRORPATH;
	}

	/**
	 * Mapping for login request
	 * Authenticate user credentials with database
	 * If successful, redirect to home page
	 * Else, display message and redirect to main page
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model) {
		String hashedPassword;
		
		try {
			hashedPassword = HashController.hash(password);
			user =  userController.getUserByUsernameAndHashedPassword(username, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null){
			model.put("message", "Invalid Credentials. Please re-login again.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			model.put("userID", user.getUserID());
			return new ModelAndView("redirect:/home");
		}
	}
	
	/**
	 * Mapping for logout request
	 * Set session userID to null, redirect to main page
	 */
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ModelAndView handleLogoutRequest(ModelMap model) {
		model.put("userID", 0);
		model.put("message", "Logged out successfully");
		model.put("redirect", "/");
		return new ModelAndView("/message");
	}
	
	@PostMapping(value="/verifyCaller")
	public ResponseEntity verifyCaller (@Valid @RequestBody Registry search, Errors errors)
	{
		Boolean result = false;

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Error");
        }

        Registry registry= registryController.getRegistryToVerify(search.getNric());

        if (registry == null || !registry.getName().equals(search.getName()) || !registry.getDob().equals(search.getDob())) {
            result = false;
        } else {
            result = true;
        }

        return ResponseEntity.ok(result);
	}
	
	@PostMapping(value="/addGraylist")
	public ResponseEntity verifyCaller (@Valid @RequestBody Graylist graylist, Errors errors)
	{
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Error from server");
        }
		try{
			List<Graylist> graylists = graylistController.getAllGraylist();
			
			for(int i = 0; i < graylists.size(); i++){
				if(graylists.get(i).getCallerNric().equals(graylist.getCallerNric()) && graylists.get(i).getReason().equals(graylist.getReason())){
		            return ResponseEntity.ok("Already graylisted with the same reason.");
				}else if(i == graylists.size() - 1){
			        graylistController.addgraylist(graylist);
			        return ResponseEntity.ok(true);
				}
			}
		}catch (Exception e) {
            return ResponseEntity.ok(e.toString());
		}
        return ResponseEntity.ok("Something went wrong while graylisting. Did you enter a proper NRIC and reason?");
	}

	@RequestMapping(value ="/addReport", method=RequestMethod.POST)
	public ModelAndView addReportPage(@RequestParam String newDateOfCall,@RequestParam String newCallStartTime, @RequestParam String newCallEndTime,
			@RequestParam String newCallLocation,@RequestParam String newCallCoordNorth, @RequestParam String newCallCoordEast,
			@RequestParam String newCallerName ,@RequestParam String newCallerIC, @RequestParam String newCallerDOB,@RequestParam String verified, 
			@RequestParam String authenticity, @RequestParam String newReason, @RequestParam String newCat, @RequestParam String newNature, 
			@RequestParam String newEstStartDate,@RequestParam String newEstStartTime, @RequestParam String inLocation,@RequestParam String incidentCoord_n,
			@RequestParam String incidentCoord_e,@RequestParam String notes, @RequestParam String action,ModelMap model) {
		
		if(model.get("userID") == null || (int) model.get("userID") == 0 || userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "Only Operators can access this page.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			Report temp= new Report();
			int userID=(int) model.get("userID");
			temp.setIncidentCategory(newCat);
			temp.setIncidentNature(newNature);
			temp.setAuthenticity(authenticity);
			temp.setDate(newDateOfCall);
			temp.setCallStartTime(newCallStartTime);
			temp.setCallEndTime(newCallEndTime);
			temp.setCallerLocation(newCallLocation);
			temp.setCallCoord_n(newCallCoordNorth);
			temp.setCallCoord_e(newCallCoordEast);
			temp.setCallerName(newCallerName);
			temp.setCallerNric(newCallerIC);
			temp.setDob(newCallerDOB);
			temp.setReason(newReason);
			temp.setIncidentDate(newEstStartDate);
			temp.setEstimatedStartTime(newEstStartTime);
			temp.setIncidentLocation(inLocation);
			temp.setIncidentCoord_n(incidentCoord_n);
			temp.setIncidentCoord_e(incidentCoord_e);
			temp.setAdditionalNotes(notes);
			if(verified.equals("Yes"))
			{
				temp.setCallerVerified(true);
			}
			else
			{
				temp.setCallerVerified(false);
			}
			if(action.equals("save"))
			{
				temp.setStatus("Submitted");
			}
			else
			{
				temp.setStatus("Drafted");
			}
			
			reportController.addReport(temp, userID);
			return new ModelAndView("redirect:/home");
		}
	}
	
	@RequestMapping(value ="/OpsUpdateReport", method=RequestMethod.POST)
	public ModelAndView opsUpdate(@RequestParam String authenticity,@RequestParam String incidentCategory,@RequestParam String incidentNature,
			@RequestParam String reportID,@RequestParam String newCallerName ,@RequestParam String newCallerIC,
			@RequestParam String newCallerDOB, @RequestParam Boolean verified, @RequestParam String reason,@RequestParam String incidentLocation,
			@RequestParam String incidentCoord_n,@RequestParam String incidentCoord_e,@RequestParam String additionalNotes,@RequestParam String action,
			ModelMap model) {
	
		if(model.get("userID") == null || (int) model.get("userID") == 0 || userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "Only Operators can access this page.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			Report temp= reportController.getReport(Integer.valueOf(reportID));
			temp.setAuthenticity(authenticity);
			temp.setIncidentNature(incidentNature);
			temp.setReason(reason);
			temp.setIncidentCategory(incidentCategory);
			temp.setIncidentLocation(incidentLocation);
			temp.setIncidentCoord_n(incidentCoord_n);
			temp.setIncidentCoord_e(incidentCoord_e);
			temp.setAdditionalNotes(additionalNotes);
			temp.setCallerName(newCallerName);
			temp.setCallerNric(newCallerIC);
			temp.setDob(newCallerDOB);
			temp.setCallerVerified(verified);
			if(action.equals("save"))
			{
				temp.setStatus("Submitted");
			}
			else
			{
				temp.setStatus("Drafted");
			}
					
			reportController.updateReport(temp, (int) model.get("userID"), Integer.valueOf(reportID));			
			return new ModelAndView("redirect:/home");
		}
	}
}