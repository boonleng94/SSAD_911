<!-- index.html -->
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<html lang="en">

	<head>
		<title>Crisis Management System | Edit Report</title>

		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap Core CSS -->
		<link rel="stylesheet" href="static/css/bootstrap.css">

		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="static/css/custom.css">

		<!--EXTERNAL API TO HANDLE GOOGLE MAPS GEOCODING-->
		<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="static/js/bootstrap.min.js"></script>

		<!-- jQuery -->
		<script src="static/js/jquery-3.2.1.min.js"></script>

		<script>
			window.selectCrisisOption = function() {
				var x = document.querySelector('input[name="crisisIDRadio"]:checked').value;
				if (document.querySelector('input[name="crisisIDRadio"]:checked').value == "choose") {
					document.getElementById('crisisIDSelector').style.display = 'inline';
					document.getElementById('crisisID').style.display = 'none';
				} else {
					document.getElementById('crisisIDSelector').style.display = 'none';
					document.getElementById('crisisID').style.display = 'inline';
				}
			}

			function setCrisisID() {
				document.getElementById('crisisID').value = document.getElementById('crisisIDSelector').value;
			}

			//TO GET COORDINATES BASED ON INPUT LOCATION USING GEOCODING///////////////
			function geocode() {
				//Get location from input field
				alert($('input[name="incidentLocation"]').val());
				var location = $('input[name="incidentLocation"]').val();
				//Get response from axios and Google Maps API
				axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
					params: {
						address: location,
						//API Key
						key: 'AIzaSyCSn7J0hFZHs7XALKzNXdfzt8-aPUcP-Ss'
					}
				}).then(function(response) {
					//if a response is recieved, display the latitude and longitude into the respective input fields
					$('input[name="incidentCoord_n"]').val(response.data.results[0].geometry.location.lat);
					$('input[name="incidentCoord_e"]').val(response.data.results[0].geometry.location.lng);
				}).catch(function(error) {
					//Catches any error and displays JS popup box 
					alert(error + ". Please check for spelling errors and be more specific with the location.");
				});
			}

			function verifyCaller() {
				var data = {};
				data["nric"] = $("#new_caller_ic").val();
				data["name"] = $("#new_caller_name").val();
				data["address"] = "";
				data["dob"] = $("#new_caller_dob").val();

				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "/verifyCaller",
					data: JSON.stringify(data),
					dataType: 'json',
					timeout: 100000,
					success: function(data) {
						console.log("SUCCESS: ", data);

						if (data == true) {
							alert("Caller is verfied");
							$("#new_caller_verified").text("Yes");
						} else {
							alert("Caller is not verfied");
							$("#new_caller_verified").text("No");
						}
					},
					error: function(e) {
						console.log("ERROR: ", e);
					},
					done: function(e) {
						alert("DONE");
					}
				});
			}

			function addGraylist() {

				if($("#new_caller_ic").val() == ""){
					alert("Please enter a NRIC");
					return;
				}
				
				var data = {};
				data["callerNric"] = $("#new_caller_ic").val();
				data["reason"] = $("#new_reason").val();
				
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "/addGraylist",
					data: JSON.stringify(data),
					dataType: 'json',
					timeout: 100000,
					success: function(data) {
						console.log("SUCCESS: ", data);

						if (data == true) {
							alert("Caller is added");
							$("#new_graylist").text("Yes");
						} else{
							alert("Caller is not added. " + data);
						}
					},
					error: function(e) {
						console.log("ERROR: ", e);
						alert("Caller is not added. " + e.responseText);
					},
					done: function(e) {
						alert("Caller is not added. " + e.responseText);
					}
				});
			}

		</script>

		<!-- Custom JavaScript -->
		<script src="static/js/main.js"></script>
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
						<li>
							<div style="margin: 15px">Logged in as: ${name} [ID: ${userID}]</div>
						</li>
						<li>
							<form class="navbar-form navbar-right" action="/logout" method="POST">
								<button type="logout" class="btn btn-secondary">Logout</button>
							</form>
						</li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="col-sm-12 text-center" style="margin-bottom: 30px">
				<h1>Edit Report ${report.reportID}</h1>
				<div class="col-sm-6 col-sm-offset-3">
					<hr>
				</div>
			</div>
			<form class="form-horizontal" action="/OpsUpdateReport" method="POST">
				<!-- left column -->
				<input type="hidden" value=${report.reportID} name="reportID" />
				<div class="col-sm-6">
					<p class="lead">Call Information</p>
					<div class="form-group">
						<label for="new_date_of_call" class="col-sm-4 control-label">Date of call<span style="color:red;">*</span> </br> (YYYY-MM-DD)</label>
						<div class="col-sm-8 entry-placeholder">
							${report.date}
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_start_time" class="col-sm-4 control-label">Call start time<span style="color:red;">*</span></label>
						<div class="col-sm-8 entry-placeholder">
							${report.callStartTime} hrs
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_end_time" class="col-sm-4 control-label">Call end time<span style="color:red;">*</span></label>
						<div class="col-sm-8 entry-placeholder">
							${report.callEndTime} hrs
						</div>
					</div>
					<div class="form-group">
						<label for="new_call_location" class="col-sm-4 control-label">Call Location<span style="color:red;">*</span></label>
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
						<div class="col-sm-8 col-sm-offset-2">
							<hr>
						</div>
					</div>

					<div class="form-group">
						<label for="new_caller_name" class="col-sm-4 control-label">Caller Name<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_name" name="newCallerName" placeholder="" value="${report.callerName}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_ic" class="col-sm-4 control-label">Caller IC Number<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_caller_ic" name="newCallerIC" placeholder="" value="${report.callerNric}" required>
						</div>
					</div>
					<div class="form-group">
						<label for="new_caller_dob" class="col-sm-4 control-label">Caller Date of Birth<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<input type="date" class="form-control" id="new_caller_dob" name="newCallerDOB" placeholder="" value="${report.dob}" required>
						</div>
				</div>
					<div class="form-group">
						<label for="new_caller_verified" class="col-sm-4 control-label">Caller Verified<span style="color:red;">*</span></label>
						<div class="col-sm-4 entry-placeholder" id="new_caller_verified" style="margin-top: 7px;">
							${report.callerVerified == true ? 'Yes' : 'No'}
						</div>
						<div class="col-sm-4">
							<input type="hidden" class="" id="new_verified" name="verified" value="${report.callerVerified}">
							<button class="btn btn-secondary btn-block" type="button" onclick="verifyCaller();">Verify Caller</button>
						</div>
				</div>
				<div class="form-group">
					<label for="new_graylist" class="col-sm-4 control-label">Caller Graylisted</label>
					<div class="col-sm-4 entry-placeholder" id="new_graylist" style="margin-top: 7px;">
						${graylisted == true ? 'Yes' : 'No'}
					</div>
					<div class="col-sm-4">
						<button class="btn btn-secondary btn-block" type="button" onclick="addGraylist();">Graylist Caller</button>
					</div>
				</div>

					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2">
							<hr>
						</div>
					</div>

					<div class="form-group">
						<label for="new_authenticity" class="col-sm-4 control-label">Authenticity of Call<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<select class="form-control" id="new_call_authenticity" name="authenticity" required>
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
							<textarea class="form-control" rows="5" id="new_reason" name="reason" style="overflow:hidden">${report.reason}</textarea>
						</div>
					</div>
				</div>

				<!-- right column -->
				<div class="col-sm-6">
					<p class="lead">Incident Details</p>
					<div class="form-group">
						<label for="new_category" class="col-sm-4 control-label">Emergency Category<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<select class="form-control" id="new_category" name="incidentCategory" required>
								<option value="" disabled selected>Select a Category</option>
								<option value="CAT1" ${report.incidentCategory == "CAT1" ? 'selected="selected"' : ''}>Category 1</option>
								<option value="CAT2" ${report.incidentCategory == "CAT2" ? 'selected="selected"' : ''}>Category 2</option>
								<option value="CAT3" ${report.incidentCategory == "CAT3" ? 'selected="selected"' : ''}>Category 3</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="new_nature" class="col-sm-4 control-label">Nature of Incident<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<select class="form-control" id="new_nature" name="incidentNature" required>
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
						<label for="new_incident_location" class="col-sm-4 control-label">Incident Location<span style="color:red;">*</span></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="new_call_location" placeholder="" name="incidentLocation" value="${report.incidentLocation}" required>
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
						<label for="new_incident_location" class="col-sm-4 control-label"></label>
						<div class="col-sm-8">
							<button class="btn btn-secondary btn-block" type="button" name="getIncidentCoordinates" onClick="geocode()">Get Coordinates</button>
						</div>
					</div>

					<div class="form-group col-sm-4" style=""></div>
					<div class="form-group col-sm-8" style="height: 20px;">
						<hr>
					</div>

					<div class="form-group" style="padding:0 15px;">
						<p class="lead" style="margin-bottom:10px;">Additional Notes</p>
						<textarea class="form-control" rows="10" id="notes" name="additionalNotes">${report.additionalNotes}</textarea>
					</div>
				</div>
				<div class="col-sm-6 col-sm-offset-3">
					<hr>
				</div>

				<div class="col-sm-12">
					<div class="form-group form-group-sm">
						<div class="col-sm-3 col-sm-offset-3">
							<button type="submit" class="btn btn-block btn-secondary" id="new_draft" name="action" value="draft">Save as Draft</button>
						</div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-block btn-primary" id="new_submit" name="action" value="save">Submit for Authentication</button>
							<!--						<button type="button" class="btn btn-block btn-primary" id="new_submit" disabled="disabled">Submit for Authentication</button>-->
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>

	</html>
