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

		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="static/css/custom.css">

		<!-- jQuery -->
		<script src="bower_components/jquery/dist/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="static/js/bootstrap.min.js"></script>  
		
		<!--EXTERNAL API TO HANDLE GOOGLE MAPS GEOCODING-->
		<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
		
		
		<!-- Custom JavaScript -->
		<script src="static/js/main.js"></script>
		
		<script>
		//TO GET COORDINATES BASED ON INPUT LOCATION USING GEOCODING///////////////
			function geocode() {
				//Get location from input field
				var location = $('input[name="InLocation"]').val();
				//Get response from axios and Google Maps API
				axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
					params: {
						address: location,
						//API Key
						key: 'AIzaSyCSn7J0hFZHs7XALKzNXdfzt8-aPUcP-Ss'
					}
				}).then(function(response) {
					//if a response is recieved, display the latitude and longitude into the respective input fields
					$('input[name="InCoordNorth"]').val(response.data.results[0].geometry.location.lat);
					$('input[name="InCoordEast"]').val(response.data.results[0].geometry.location.lng);
				}).catch(function(error) {
					//Catches any error and displays JS popup box 
					alert(error + ". Please check for spelling errors and be more specific with the location.");
				});
			}
		
		</script>

		<title>Crisis Management System | New Report</title>
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
				<h1>New Report</h1>
				<div class="col-sm-6 col-sm-offset-3"><hr></div>
			</div>
			<form class="form-horizontal" action="/addReport" method="POST">
				<!-- left column -->
				<div class="col-sm-6">
					<p class="lead">Call Information</p>
					<div class="form-group">
						<label for="new_date_of_call" class="col-sm-4 control-label">Date of call</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_date_of_call" name="newDateOfCall" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_start_time" class="col-sm-4 control-label">Call start time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_call_start_time" name="newCallStartTime" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_end_time" class="col-sm-4 control-label">Call end time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_call_end_time" name="newCallEndTime" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_location" class="col-sm-4 control-label">Call Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" name="newCallLocation" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_coordinates" class="col-sm-4 control-label">Call Coordinates</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_call_coordinates_north" name="newCallCoordNorth" placeholder="">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">N</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_call_coordinates_east" name="newCallCoordEast" placeholder="">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">E</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4 col-sm-offset-4">
							<button class="btn btn-secondary btn-block" type="submit">Get Coordinates</button>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_caller_name" class="col-sm-4 control-label">Caller Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_name" name="newCallerName" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_ic" class="col-sm-4 control-label">Caller IC Number</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_ic" name = "newCallerIC"placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_dob" class="col-sm-4 control-label">Caller Date of Birth</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_caller_dob" name="newCallerDOB" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_verified" class="col-sm-4 control-label" >Caller Verified</label>
						<div class="col-sm-4 entry-placeholder" id="new_caller_verified"  style="margin-top: 7px;">
							No
						</div>
						<div class="col-sm-4">
							<button class="btn btn-secondary btn-block" type="submit">Verify Caller</button>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_authenticity" class="col-sm-4 control-label">Authenticity of Call<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<select class="form-control" id="new_call_authenticity" name="authenticity">
								<option value="" disabled selected>Select an option</option>
								<option value="Unsure">Unsure</option>
								<option value="Authentic">Authentic</option>
								<option value="Prank">Prank Call</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="new_reason" class="col-sm-4 control-label">Reason</label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="3" id="new_reason" name="newReason"></textarea>
						</div>
					</div>
				</div>

				<!-- right column -->
				<div class="col-sm-6">
					<p class="lead">Incident Details</p>
					<div class="form-group">
						<label for="new_category" class="col-sm-4 control-label">Emergency Category</label>
						<div class="col-sm-8">
							<select class="form-control" id="new_category" name="newCat">
								<option value="" disabled selected>Select a Category</option>
								<option value="CAT1">Category 1</option>
								<option value="CAT2">Category 2</option>
								<option value="CAT3">Category 3</option>
							</select>
						</div>
						
					</div>
					<div class="form-group">
						<label for="new_nature" class="col-sm-4 control-label">Nature of Incident</label>
						<div class="col-sm-8">
							<select class="form-control" id="new_nature" name="newNature">
								<option value="" disabled selected>Select an option</option>
								<option value="Aggravated Assault">Aggravated Assault</option>
								<option value="Arson">Arson</option>
								<option value="Animal Abuse">Animal Abuse</option>
								<option value="Burglary">Burglary</option>
								<option value="Civil Unrest">Civil Unrest</option>
								<option value="Homicide">Criminal Homicide</option>
								<option value="Death">Death</option>
								<option value="Disease Outbreak">Disease Outbreak</option>
								<option value="Domestic Violence">Domestic Violence</option>
								<option value="Hate Crime">Hate Crime</option>
								<option value="Invasion">Invasion</option>
								<option value="Larceny">Larceny</option>
								<option value="Missing Person">Missing Person</option>
								<option value="Motor Vehicle Accident">Motor Vehicle Accident</option>
								<option value="Natural Disaster">Natural Disaster</option>
								<option value="Possession of Contrabrand">Possession of Contrabrand</option>
								<option value="Robbery">Robbery</option>
								<option value="Sexual Assault">Sexual Assault</option>
								<option value="Sexual Harassment">Sexual Harassment</option>
								<option value="Simple Assault">Simple Assault</option>
								<option value="Smuggling">Smuggling</option>
								<option value="Terrorism">Terrorism</option>
								<option value="Theft">Theft</option>
								<option value="Other">Other</option>
							</select>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_date" class="col-sm-4 control-label">Estimated Start Date</label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_estimated_start_date" name="newEstStartDate" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_time" class="col-sm-4 control-label">Estimated Start Time</label>
						<div class="col-sm-8">
							<input type="time" class="form-control" id="new_estimated_start_time" name="newEstStartTime" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_location" class="col-sm-4 control-label">Incident Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" name="InLocation" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_coordinates" class="col-sm-4 control-label">Incident Coordinates</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_incident_coordinates_north" name="InCoordNorth" placeholder="">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">N</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="new_incident_coordinates_east" name="InCoordEast" placeholder="">
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">E</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4 col-sm-offset-4">
							<button class="btn btn-secondary btn-block" type="button" name="getIncidentCoordinates" onclick="geocode();">Get Coordinates</button>
						</div>
					</div>
					<div class="form-group col-sm-4"></div>
					<div class="form-group col-sm-8" style="height: 40px;"><hr></div>
					<div class="form-group" style="padding:0 15px;">
						<p class="lead">Additional Notes</p>
						<textarea class="form-control" rows="10" id="notes" name="notes"></textarea>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-3"><hr></div>

				<div class="col-sm-12">
					<div class="form-group form-group-sm">
						<div class="col-sm-3 col-sm-offset-3">
							<button type="submit" class="btn btn-block btn-secondary" id="new_submit"name="action" value="draft">Save as Draft</button>
						</div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-block btn-primary" id="new_submit" name="action" value="save" >Submit for Authentication</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>