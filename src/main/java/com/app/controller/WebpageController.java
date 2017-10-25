package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.cmoapi.NineOneOneClient;
import com.app.report.Report;
import com.app.report.ReportController;
import com.app.user.User;
import com.app.user.UserController;
import com.app.geocoder.*;

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
			else
				model.put("reportList", reportController.getAllOperatorReports((int) model.get("userID")));
				return new ModelAndView("operatorhome");
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
			return new ModelAndView("officereditreport");
		}
		else {
			user =  userController.getUserByUserID((int) model.get("userID"));
			model.put("name", user.getName());
			return new ModelAndView("newreport");
		}
	}
	
	/**
	 * Mapping for saving POSTed report to update
	 */
	@RequestMapping(value ="/officerUpdateReport", method=RequestMethod.POST)
	public ModelAndView updateReportPage(@RequestParam String reportID, @RequestParam String date, @RequestParam String callStartTime, @RequestParam String callEndTime, 
			@RequestParam String callerLocation, @RequestParam String callCoord_n, @RequestParam String callCoord_e, @RequestParam String callerName, @RequestParam String callerNric, 
			@RequestParam String dob, @RequestParam String callerVerified, @RequestParam String authenticity, @RequestParam String reason, @RequestParam String incidentCategory, 
			@RequestParam String incidentNature, @RequestParam String incidentDate, @RequestParam String estimatedStartTime, /*@RequestParam String estimatedEndTime, */
			@RequestParam String noOfCasualties, @RequestParam String incidentLocation, @RequestParam String incidentCoord_n, @RequestParam String incidentCoord_e, 
			@RequestParam String additionalNotes, @RequestParam String operatorUserID, @RequestParam String crisisID, @RequestParam String action, ModelMap model) {
		
		if(model.get("userID") == null || (int) model.get("userID") == 0 || !userController.isLiaisonOfficer((int) model.get("userID"))) {
			model.put("message", "Only Liaison Officers can access this page.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		}
		else{
			model.put("name", user.getName());
			model.put("report", reportController.getReport(Integer.valueOf(reportID))/*Sends report object. To call variable, use report.(name)*/);
			//STORE POST DATA INTO MODEL
			Report tempReport = new Report(Integer.parseInt(reportID), date, callStartTime, callEndTime, callerLocation, callCoord_n, callCoord_e, callerName, callerNric, dob,
					((callerVerified.equals("Yes")) ? true  : false), authenticity, reason, incidentCategory, incidentNature, incidentDate, estimatedStartTime, "0000000", 
					Integer.parseInt(noOfCasualties), incidentLocation, incidentCoord_n, incidentCoord_e, additionalNotes, Integer.parseInt(operatorUserID), "Verified", (int) model.get("userID"), Integer.parseInt(crisisID));
			
			Report savedReport = reportController.updateReportOfficer(tempReport, (int) model.get("userID"), Integer.parseInt(reportID));
			
			if(action.equals("submit")){
				User user = userController.getUserToSubmitReport(savedReport.getOfficerUserID());
				NineOneOneClient.createCallReport(savedReport, user);
				savedReport.setStatus("Sent");
				Report submittedReport = reportController.updateReportOfficer(savedReport, (int) model.get("userID"), Integer.parseInt(reportID));
			}
			
			//Everything is updated, but error message still returns page not available
			return new ModelAndView("/home");
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
}
