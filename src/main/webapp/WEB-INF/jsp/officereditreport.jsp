<!-- index.html -->
<!DOCTYPE html>
<html lang="en">

	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap Core CSS -->
		<!--	<link rel="stylesheet" href="static/css/bootstrap.min.css">-->
		<link rel="stylesheet" href="static/css/bootstrap.css">

		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="static/css/custom.css">

		<!-- jQuery -->
		<script src="bower_components/jquery/dist/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="static/js/bootstrap.min.js"></script>  

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
				<input type="hidden" value=${report.operatorUserID} name="operatorUserID"/>
				<div class="col-sm-6">
					<p class="lead">Call Information</p>
					<div class="form-group">
						<label for="new_date_of_call" class="col-sm-4 control-label">Date of call</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_date_of_call" placeholder="" value="13-Jan-2017" name="date">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_start_time" class="col-sm-4 control-label">Call start time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_call_start_time" placeholder="" name="callStartTime" value="${report.callStartTime}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_end_time" class="col-sm-4 control-label">Call end time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_name" placeholder="" name="callEndTime" value="${report.callEndTime}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_location" class="col-sm-4 control-label">Call Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" placeholder="" name="callerLocation" value="${report.callerLocation}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_coordinates" class="col-sm-4 control-label">Call Coordinates</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_call_coordinates_north" placeholder="" name="callCoord_n" value="${report.callCoord_n}"	>
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">N</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_call_coordinates_east" placeholder="" name="callCoord_e" value="${report.callCoord_e}">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">E</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4 col-sm-offset-4">
							<button class="btn btn-secondary btn-block" type="button" name="getCoords" value="getCoords" >Get Coordinates</button>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_caller_name" class="col-sm-4 control-label">Caller Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_name" placeholder="" name="callerName" value="${report.callerName}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_ic" class="col-sm-4 control-label">Caller IC Number</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_ic" placeholder="" name="callerNric" value="${report.callerNric}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_dob" class="col-sm-4 control-label">Caller Date of Birth</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_caller_dob" placeholder="" name="dob" value="${report.callStartTime}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_verified" class="col-sm-4 control-label">Caller Verified</label>
						<div class="col-sm-4 entry-placeholder" id="new_caller_verified" style="margin-top: 7px;">
							${report.callerVerified == false ? "No" : "Yes"}
							<input type="hidden" value=${report.callerVerified} name="callerVerified"/>
						</div>
						<div class="col-sm-4">
							<button class="btn btn-secondary btn-block" type="button" name="verifyCaller" name="verifyCaller" >Verify Caller</button>
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
							<textarea class="form-control" rows="3" id="new_reason" name="reason">${report.reason}</textarea>
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
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_date" class="col-sm-4 control-label">Estimated Start Date</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_estimated_start_date" placeholder="" name="incidentDate" value="${report.incidentDate}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_time" class="col-sm-4 control-label">Estimated Start Time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_estimated_start_time" placeholder="" name="estimatedStartTime" value="${report.callStartTime}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_location" class="col-sm-4 control-label">Incident Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" placeholder="" name="incidentLocation" value="${report.incidentLocation}">
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_coordinates" class="col-sm-4 control-label">Incident Coordinates</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_incident_coordinates_north" placeholder="" name="incidentCoord_n" value="${report.incidentCoord_n}">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">N</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_incident_coordinates_east" placeholder="" name="incidentCoord_e" value="${report.incidentCoord_e}">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">E</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4 col-sm-offset-4">
							<button class="btn btn-secondary btn-block" type="submit" name="" onclick="location.href='/livefeed'" value="getIncidentCoords">Get Coordinates</button>
						</div>
					</div>
					<div class="form-group col-sm-4"></div>
					<div class="form-group col-sm-8" style="height: 40px;"><hr></div>
					<div class="form-group" style="padding:0 15px;">
						<p class="lead">Additional Notes</p>
						<textarea class="form-control" rows="10" id="notes" name="additionalNotes">${report.additionalNotes}</textarea>
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