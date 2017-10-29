package com.app.controller;

import java.util.Collections;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.cmoapi.NineOneOneClient;
import com.app.geocoder.*;
import com.app.report.Report;
import com.app.report.ReportController;
import com.app.user.User;
import com.app.user.UserController;
import com.mysql.fabric.xmlrpc.base.Array;

import scala.annotation.meta.field;

@Controller
@SessionAttributes("userID")
public class WebpageController implements ErrorController{
	
	@Autowired
	UserController userController;
	@Autowired 
	GeocoderController geocodercontroller;	
	@Autowired
	ReportController reportController;
	
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

		//if session has user
		if(userInSession == 0)
			return new ModelAndView("login");
		//else go to homepage
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
				//DRAFTED / SUBMITTED / VERIFIED / SENT
				
//				for (Report report : reports) {
//					String startDateString = "06/27/2007";
//					DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
//					Date startDate;
//					try {
//					    startDate = df.parse(startDateString);
//					    report.setDate(df.format(startDate));
//					} catch (ParseException e) {
//					    e.printStackTrace();
//					}
//				}

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
			model.put("report", reportController.getReport(Integer.valueOf(reportID)));
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
			model.put("report", reportController.getReport(Integer.valueOf(reportID))/*Sends report object. To call variable, use report.(name)*/);
			//STORE POST DATA INTO MODEL
			
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
	//@RequestMapping(ERRORPATH)
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
	@RequestMapping(value="/getgeo")
	public ModelAndView getGeoCode (ModelMap model,@RequestParam String address, @RequestParam String reportID)
	{
		Response response=null;
		try {
			 response= geocodercontroller.getGeoCode(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		results[] Result=response.getResults();
		geometry geo = Result[2].getGeometry();//Change number if query does not work
		location loc =geo.getLocation();
		model.put("lat",loc.getLat() );
		model.put("lng", loc.getLng());
		
		//update database
		
		//send post with report ID parameter to editreport
		model.put("reportID", reportID);
		return new ModelAndView("redirect:/editReport");//check user service 
	}

	@RequestMapping(value ="/addReport", method=RequestMethod.POST)//Done but need to map to jsp
	public ModelAndView addReportPage(@RequestParam String authenticity,@RequestParam String newCat,@RequestParam String newNature,
			@RequestParam String newDateOfCall,@RequestParam String newCallStartTime
			,@RequestParam String newCallEndTime,@RequestParam String newCallLocation,@RequestParam String newCallCoordNorth, 
			@RequestParam String newCallCoordEast,@RequestParam String newCallerName ,@RequestParam String newCallerIC,
			@RequestParam String newCallerDOB,@RequestParam String newReason,
			@RequestParam String newEstStartDate,@RequestParam String newEstStartTime,@RequestParam String InLocation,
			@RequestParam String InCoordNorth,@RequestParam String InCoordEast,@RequestParam String notes,
			@RequestParam String action,ModelMap model) {
		
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
			temp.setIncidentLocation(InLocation);
			temp.setIncidentCoord_n(InCoordNorth);
			temp.setIncidentCoord_e(InCoordEast);
			temp.setAdditionalNotes(notes);
			if(action.equals("save"))
			{
				if(newCat.equals("CAT 1"))
				{
					temp.setStatus("Saved,Awaiting LO action");
				}
				else
				{
				temp.setStatus("Saved");
				}
			}
			else
			{
				temp.setStatus("Saved as Draft");
			}
			
			reportController.addReport(temp, userID);
			//need to set officer if not officer cannot view
			//model.put("report", reportController.getReport(reportID)/*Sends report object. To call variable, use report.(name)*/);
			//STORE POST DATA INTO MODEL
			
			System.out.println("newDateOfCall: "+newDateOfCall);
			System.out.println("newCallStartTime: "+newCallStartTime);
			System.out.println("newCallEndTime: "+newCallEndTime);
			System.out.println("newCallLocation: "+newCallLocation);
			System.out.println("newCallCoordNorth: "+newCallCoordNorth);
			System.out.println("newCallCoordEast: "+newCallCoordEast);
			System.out.println("newCallerName: "+newCallerName);
			System.out.println("newCallerIC: "+newCallerIC);
			System.out.println("newCallerDOB: "+newCallerDOB);
			System.out.println("newReason: "+newReason);
			System.out.println("newEstStartDate: "+newEstStartDate);
			System.out.println("newEstStartTime: "+newEstStartTime);
			System.out.println("InLocation: "+InLocation);
			System.out.println("InCoordNorth: "+InCoordNorth);
			System.out.println("InCoordEast: "+InCoordEast);
			System.out.println("notes: "+notes);
			System.out.println("action: "+action);

			//reportController.updateReport(updatedReport, (int) model.get("userID"), reportID);
			
			return new ModelAndView("redirect:/home");
		}
	}
	
	
	@RequestMapping(value ="/OpsUpdateReport", method=RequestMethod.POST)//Done but need to map to jsp
	public ModelAndView opsUpdate(@RequestParam String authenticity,@RequestParam String incidentCategory,@RequestParam String incidentNature,@RequestParam String reportID,@RequestParam String reason,@RequestParam String incidentLocation,
			@RequestParam String incidentCoord_n,@RequestParam String incidentCoord_e,@RequestParam String additionalNotes,
			ModelMap model) {
		
		if(model.get("userID") == null || (int) model.get("userID") == 0 || userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "Only Operators can access this page.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			System.out.println(incidentCategory);
			Report temp= reportController.getReport(Integer.valueOf(reportID));
			temp.setAuthenticity(authenticity);
			temp.setIncidentNature(incidentNature);
			temp.setReason(reason);
			temp.setIncidentCategory(incidentCategory);
			temp.setIncidentLocation(incidentLocation);
			temp.setIncidentCoord_n(incidentCoord_n);
			temp.setIncidentCoord_e(incidentCoord_e);
			temp.setAdditionalNotes(additionalNotes);
					
			reportController.updateReport(temp, (int) model.get("userID"), Integer.valueOf(reportID));			
			return new ModelAndView("redirect:/home");
		}
	}
	
}
