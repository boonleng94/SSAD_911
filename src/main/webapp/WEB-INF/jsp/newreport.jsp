<!-- index.html -->
<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="static/css/bootstrap.min.css">

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="static/css/custom.css">

	<!-- jQuery -->
	<script src="bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="static/js/bootstrap.min.js"></script>  

	<!-- Custom JavaScript -->
	<script src="static/js/main.js"></script>

	<title>911 Call Center</title>
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
	      <a class="navbar-brand" href="#">911 Call Center</a>
	    </div>

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="#">Logged in as: Operator ${userID}, ${name}</a></li>
	      </ul>
	      <form class="navbar-form navbar-right" action="/logout" method="POST">
	        <button type="logout" class="btn btn-secondary">Logout</button>
	      </form>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div class="container">
		<form class="form-horizontal">
			<!-- left column -->
			<div class="col-sm-6">
				<p class="lead text-center">Call Information</p>
				<div class="form-group">
					<label for="new_date_of_call" class="col-sm-4 control-label">Date of call</label>
					<div class="col-sm-8">
						<input type="date" class="form-control" id="new_date_of_call" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_call_start_time" class="col-sm-4 control-label">Call start time</label>
					<div class="col-sm-8">
						<input type="time" class="form-control" id="new_call_start_time" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_call_end_time" class="col-sm-4 control-label">Call end time</label>
					<div class="col-sm-8">
						<input type="time" class="form-control" id="new_name" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_call_location" class="col-sm-4 control-label">Call Location</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="new_call_location" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_call_coordinates" class="col-sm-4 control-label">Call Coordinates</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="new_call_coordinates_north" placeholder="">
					</div>
					<div class="col-sm-1">N</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4"></div>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="new_call_coordinates_east" placeholder="">
					</div>
					<div class="col-sm-1">E</div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-6"></div>
					<div class="col-sm-4">
						<button class="btn btn-default" type="submit">Get Coordinates</button>
					</div>
				</div>
				<div class="form-group col-sm-4"></div>
				<div class="form-group col-sm-8"><hr></div>
				<div class="form-group">
					<label for="new_caller_name" class="col-sm-4 control-label">Caller Name</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="new_caller_name" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_caller_ic" class="col-sm-4 control-label">Caller IC Number</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="new_caller_ic" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_caller_dob" class="col-sm-4 control-label">Caller Date of Birth</label>
					<div class="col-sm-8">
						<input type="date" class="form-control" id="new_caller_dob" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_caller_ic" class="col-sm-4 control-label">Caller Verified</label>
					<div class="col-sm-4">
						No
					</div>
					<div class="col-sm-4">
						<button class="btn btn-default" type="submit">Verify Caller</button>
					</div>
				</div>
				<div class="form-group col-sm-4"></div>
				<div class="form-group col-sm-8"><hr></div>
				<div class="form-group">
					<label for="new_authenticity" class="col-sm-4 control-label">Authenticity of Call</label>
					<div class="col-sm-8">
						<select class="form-control" id="new_call_authenticity">
							<option value="unsure">Unsure</option>
							<option value="authentic">Authentic</option>
							<option value="prank">Prank Call</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="new_reason" class="col-sm-4 control-label">Reason</label>
					<div class="col-sm-8">
						<textarea class="form-control" rows="3" id="new_reason"></textarea>
					</div>
				</div>
			</div>

			<!-- right column -->
			<div class="col-sm-6">
				<p class="lead text-center">Incident Details</p>
				<div class="form-group">
					<label for="new_category" class="col-sm-4 control-label">Emergency Category</label>
					<div class="col-sm-8">
						<select class="form-control" id="new_category">
							<option value="1">Category 1</option>
							<option value="2">Category 2</option>
							<option value="3">Category 3</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="new_nature" class="col-sm-4 control-label">Nature of Incident</label>
					<div class="col-sm-8">
						<select class="form-control" id="new_nature">
							<option value="1">Option 1</option>
							<option value="2">Option 2</option>
							<option value="3">Option 3</option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-4"></div>
				<div class="form-group col-sm-8"><hr></div>
				<div class="form-group">
					<label for="new_estimated_start_date" class="col-sm-4 control-label">Estimated Start Date</label>
					<div class="col-sm-8">
						<input type="date" class="form-control" id="new_estimated_start_date" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_estimated_start_time" class="col-sm-4 control-label">Estimated Start Time</label>
					<div class="col-sm-8">
						<input type="time" class="form-control" id="new_estimated_start_time" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="new_incident_location" class="col-sm-4 control-label">Estimated Incident Location</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="new_call_location" placeholder="">
					</div>
				</div>
				<div class="form-group col-sm-4"></div>
				<div class="form-group col-sm-8"><hr></div>
				<p class="lead text-center">Additional Notes</p>
				<div class="form-group col-sm-12">
					<textarea class="form-control" rows="16" id="notes"></textarea>
				</div>
			</div>
			<div class="col-sm-6 col-sm-offset-3"><hr></div>
			<div class="form-group form-group-sm">
				<div class="col-sm-2 col-sm-offset-5">
					<button type="button" class="btn btn-primary" id="new_submit" disabled="disabled">Create New Report</button>
				</div>
			</div>      
		</form>
	</div>
</body>
</html>