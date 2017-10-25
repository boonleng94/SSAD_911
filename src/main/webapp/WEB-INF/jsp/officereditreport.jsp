<!-- index.html -->
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap Core CSS -->
		<!--	<link rel="stylesheet" href="static/css/bootstrap.min.css">-->
		<link rel="stylesheet" href="static/css/bootstrap.css">
<!--
		<link href="static/css/style.css" rel="stylesheet">
		<link href="static/css/login.css" rel="stylesheet" >
-->
		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="static/css/custom.css">
		

		<!-- Bootstrap Core JavaScript -->
		<script src="static/js/bootstrap.min.js"></script>  
		<!-- jQuery -->
		<script src="static/js/jquery-1.11.1.min.js"></script>

		<script>
			function selectC() {
				document.getElementById('crisisIDSelector').style.display = 'block';
				document.getElementById('crisisID').style.display = 'none';
			}

			function create() {
				document.getElementById('crisisIDSelector').style.display = 'none';
				document.getElementById('crisisID').style.display = 'block';
			}
			
			function setCrisisID() {
				document.getElementById('crisisID').value=document.getElementById('crisisIDSelector').value;
			}
		</script>

		<!-- Custom JavaScript -->
		<script src="static/js/main.js"></script>

		<title>Crisis Management System | Edit Report</title>
	</head>

	<body>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/home">Crisis Management System: 911 Call Center</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li><div style="margin: 15px">Logged in as: ${name} [ID: ${userID}]</div></li>
						<li>
							<form class="navbar-form navbar-right" action="/logout" method="POST">
								<button type="logout" class="btn btn-secondary">Logout</button>
							</form>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>

		<div class="container">
			<div class="col-sm-12 text-center" style="margin-bottom: 30px">
				<h1>Edit Report ${report.reportID}</h1>
				<div class="col-sm-6 col-sm-offset-3"><hr></div>
			</div>
			<form class="form-horizontal" action="/officerUpdateReport" method="POST">
				<!-- left column -->
				<input type="hidden" value=${report.reportID} name="reportID"/>
				<div class="col-sm-6">
					<p class="lead">Call Information</p>
					<div class="form-group">
						<label for="new_date_of_call" class="col-sm-4 control-label">Date of call </br> (YYYY-MM-DD)</label>
						<div class="col-sm-8 entry-placeholder">
							${report.date}
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_start_time" class="col-sm-4 control-label">Call start time</label>
						<div class="col-sm-8 entry-placeholder">
							${report.callStartTime} hrs
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_end_time" class="col-sm-4 control-label">Call end time</label>
						<div class="col-sm-8 entry-placeholder">
							${report.callEndTime} hrs
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_location" class="col-sm-4 control-label">Call Location</label>
						<div class="col-sm-8 entry-placeholder">
							${report.callerLocation}
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
						<label for="new_call_coordinates" class="col-sm-4 control-label">Call Coordinates</label>
						<div class="col-sm-8 entry-placeholder">
							${report.callCoord_n} Latitude, ${report.callCoord_e} Longitude
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					
					<div class="form-group">
						<label for="new_caller_verified" class="col-sm-4 control-label">Caller Verified</label>
						<div class="col-sm-4 entry-placeholder" id="new_caller_verified" style="margin-top: 7px;">
							${report.callerVerified == false ? "No" : "Yes"}
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					
					<div class="form-group">
						<label for="new_authenticity" class="col-sm-4 control-label">Authenticity of Call</label>
						<div class="col-sm-8">
							<select class="form-control" id="new_call_authenticity" name="authenticity">
								<option value="" disabled selected>Select an option</option>
								<option value="Unsure" ${report.authenticity == "Unsure" ? 'selected="selected"' : ''}>Unsure</option>
								<option value="Authentic" ${report.authenticity == "Authentic" ? 'selected="selected"' : ''}>Authentic</option>
								<option value="Prank" ${report.authenticity == "Prank" ? 'selected="selected"' : ''}>Prank Call</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="new_reason" class="col-sm-4 control-label">Reason</label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5" id="new_reason" name="reason">${report.reason}</textarea>
						</div>
					</div>
				</div>

				<!-- right column -->
				<div class="col-sm-6">
					<p class="lead">Incident Details</p>
					<div class="form-group">
						<label for="new_category" class="col-sm-4 control-label">Emergency Category</label>
						<div class="col-sm-8">
							<select class="form-control" id="new_category" name="incidentCategory">
								<option value="" disabled selected>Select a Category</option>
								<option value="CAT1" ${report.incidentCategory == "CAT1" ? 'selected="selected"' : ''}>Category 1</option>
								<option value="CAT2" ${report.incidentCategory == "CAT2" ? 'selected="selected"' : ''}>Category 2</option>
								<option value="CAT3" ${report.incidentCategory == "CAT3" ? 'selected="selected"' : ''}>Category 3</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="new_nature" class="col-sm-4 control-label">Nature of Incident</label>
						<div class="col-sm-8">
							<select class="form-control" id="new_nature" name="incidentNature">
								<option value="" disabled selected>Select an option</option>
								<option value="Aggravated Assault" ${report.incidentCategory == "Aggravated Assault" ? 'selected="selected"' : ''}>Aggravated Assault</option>
								<option value="Arson" ${report.incidentNature == "Arson" ? 'selected="selected"' : ''}>Arson</option>
								<option value="Animal Abuse" ${report.incidentNature == "Animal Abuse" ? 'selected="selected"' : ''}>Animal Abuse</option>
								<option value="Burglary" ${report.incidentNature == "Burglary" ? 'selected="selected"' : ''}>Burglary</option>
								<option value="Civil Unrest" ${report.incidentNature == "Civil Unrest" ? 'selected="selected"' : ''}>Civil Unrest</option>
								<option value="Homicide" ${report.incidentNature == "Homicide" ? 'selected="selected"' : ''}>Criminal Homicide</option>
								<option value="Death" ${report.incidentNature == "Death" ? 'selected="selected"' : ''}>Death</option>
								<option value="Disease Outbreak" ${report.incidentNature == "Disease Outbreak" ? 'selected="selected"' : ''}>Disease Outbreak</option>
								<option value="Domestic Violence" ${report.incidentNature == "Domestic Violence" ? 'selected="selected"' : ''}>Domestic Violence</option>
								<option value="Hate Crime" ${report.incidentNature == "Hate Crime" ? 'selected="selected"' : ''}>Hate Crime</option>
								<option value="Invasion" ${report.incidentNature == "Invasion" ? 'selected="selected"' : ''}>Invasion</option>
								<option value="Larceny" ${report.incidentNature == "Larceny" ? 'selected="selected"' : ''}>Larceny</option>
								<option value="Missing Person" ${report.incidentNature == "Missing Person" ? 'selected="selected"' : ''}>Missing Person</option>
								<option value="Motor Vehicle Accident" ${report.incidentNature == "Motor Vehicle Accident" ? 'selected="selected"' : ''}>Motor Vehicle Accident</option>
								<option value="Natural Disaster" ${report.incidentNature == "Natural Disaster" ? 'selected="selected"' : ''}>Natural Disaster</option>
								<option value="Possession of Contrabrand" ${report.incidentNature == "Possession of Contrabrand" ? 'selected="selected"' : ''}>Possession of Contrabrand</option>
								<option value="Robbery" ${report.incidentNature == "Robbery" ? 'selected="selected"' : ''}>Robbery</option>
								<option value="Sexual Assault" ${report.incidentNature == "Sexual Assault" ? 'selected="selected"' : ''}>Sexual Assault</option>
								<option value="Sexual Harassment" ${report.incidentNature == "Sexual Harassment" ? 'selected="selected"' : ''}>Sexual Harassment</option>
								<option value="Simple Assault" ${report.incidentNature == "Simple Assault" ? 'selected="selected"' : ''}>Simple Assault</option>
								<option value="Smuggling" ${report.incidentNature == "Smuggling" ? 'selected="selected"' : ''}>Smuggling</option>
								<option value="Terrorism" ${report.incidentNature == "Terrorism" ? 'selected="selected"' : ''}>Terrorism</option>
								<option value="Theft" ${report.incidentNature == "Theft" ? 'selected="selected"' : ''}>Theft</option>
								<option value="Other" ${report.incidentNature == "Other" ? 'selected="selected"' : ''}>Other</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="new_estimated_start_date" class="col-sm-4 control-label">Estimated Start Date</label>
						<div class="col-sm-8 entry-placeholder">
							${report.incidentDate}
						</div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_time" class="col-sm-4 control-label">Estimated Start Time</label>
						<div class="col-sm-8 entry-placeholder">
							${report.callStartTime} hrs
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_location" class="col-sm-4 control-label">Incident Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" placeholder="" name="incidentLocation" value="${report.incidentLocation}">
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
						<label for="new_incident_coordinates" class="col-sm-4 control-label">Incident Coordinates</label>
						<div class="col-sm-3" style="width: 20%; padding-right: 5px;">
							<input type="text" class="form-control" id="new_incident_coordinates_north" placeholder="" name="incidentCoord_n" value="${report.incidentCoord_n}">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px; padding:0;">Latitude</div>
						<div class="col-sm-3" style="width: 20%; padding-right: 5px;">
							<input type="text" class="form-control" id="new_incident_coordinates_east" placeholder="" name="incidentCoord_e" value="${report.incidentCoord_e}">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px; padding:0;">Longitude</div>
					</div>
					
					<div class="form-group">
						<div class="" style="width: 95%; margin:auto;">
<!--							<form class="navbar-form navbar-right" action="#" method="POST" target="_blank">-->
							<button class="btn btn-secondary btn-block" type="button" name="checkLiveFeeds" value="checkLiveFeeds" onClick="window.open('http://localhost:8080/livefeed', 'b', 'height=750,width=768'); return false;">Check Live Feed</button>
<!--							</form>-->
						</div>
					</div>

					<div class="col-sm-12" style="height:30px;">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					
					<div class="form-group" style="padding:0 15px; ">
						<p class="lead" style="margin-bottom:0px">Crisis ID</p>
					</div>
					<div class="form-group">
						<label class="col-sm-6 control-label"><input type="radio" name="crisisIDRadio" class="col-sm-1 control-label" value="choose" onchange="selectC();" checked> Choose from existing Crisis ID</label>
						<label class="col-sm-6 control-label"><input type="radio" name="crisisIDRadio" class="col-sm-1 control-label" value="create" onchange="create();"> Create new Crisis ID</label>
					</div>
					<div class="form-group">
						
						<div class="col-sm-8" style="padding-right: 5px;">
							<select class="form-control" id="crisisIDSelector" name="crisisIDSelector" onchange="setCrisisID();">
								<option value="" disabled selected>Select an option</option>
								<c:forEach items="${crisisIDs}" var="cid"> 
									<c:choose>
										<c:when test = "${cid == report.crisisID}">
											<option value="${cid}" selected>${cid}</option>
										</c:when>    
										<c:otherwise>
											<option value="${cid}">${cid}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<input type="text" class="form-control" id="crisisID" placeholder="" name="crisisID" value="${report.crisisID}" style="display:none;">
						</div>
					</div>

					<div class="form-group col-sm-4" style=""></div>
					<div class="form-group col-sm-8" style="height: 20px;"><hr></div>
					
					<div class="form-group" style="padding:0 15px;">
						<p class="lead" style="margin-bottom:10px;">Additional Notes</p>
						<textarea class="form-control" rows="5" id="notes" name="additionalNotes">${report.additionalNotes}</textarea>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-3"><hr></div>

				<div class="col-sm-12">
					<div class="form-group form-group-sm">
						<div class="col-sm-3 col-sm-offset-3">
							<button type="submit" class="btn btn-block btn-secondary" id="new_draft" name="action" value="save">Save as Draft</button>
						</div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-block btn-primary" id="new_submit" name="action" value="submit">Submit for Authentication</button>
<!--						<button type="button" class="btn btn-block btn-primary" id="new_submit" disabled="disabled">Submit for Authentication</button>-->
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>