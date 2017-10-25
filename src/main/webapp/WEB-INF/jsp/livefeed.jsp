<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="static/js/login.js"></script>

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="static/css/custom.css">

    <title>Crisis Management System | Home</title>
    <style type="text/css">
        .auto-style1 {
            width: 612px;
        }
    </style>
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
                </ul><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </div>
    </nav>

    <div class="container">
        <div class="table-responsive">
            <div class="col-sm-12 text-center" style="margin-bottom: 30px">
                <h1>Live Feed</h1>
                <div class="col-sm-6 col-sm-offset-3"><hr></div>
            </div>
            <table class="table table-striped">
                <thead>
                </thead>
                <tbody>
                    <tr>
                        <td class="auto-style1">
                            <div class="table-entry">
								<img src="http://10.27.131.195:6888/video/live.mjpg?id=6d5529e2-2dd4-1d3e-bd77-8093d6ca0347" height="280" width ="280" alt="live-feed-video"/>
								<img src="http://10.27.131.195:6888/video/live.mjpg?id=6d5529e2-2dd4-1d3e-bd77-8093d6ca0347" height="280" width ="280" alt="live-feed-video"/>
								<img src="http://10.27.131.195:6888/video/live.mjpg?id=6d5529e2-2dd4-1d3e-bd77-8093d6ca0347" height="280" width ="280" alt="live-feed-video"/>
								<img src="http://10.27.131.195:6888/video/live.mjpg?id=6d5529e2-2dd4-1d3e-bd77-8093d6ca0347" height="280" width ="280" alt="live-feed-video"/>
								<!--<img src="http://192.168.1.119:6888/video/live.mjpg?id=39e327af-cc42-d083-0f65-32453e2c13b2" height="1120" width ="1120" alt="live-feed-video"/> -->
                            </div>
                        </td>
<!--
						<td>
					<p class="lead">Crisis Details</p>
					<div class="form-group">
						<label for="new_category" class="col-sm-4 control-label">Emergency Category</label>
						<div class="col-sm-8">
							<label class="form-control" id="new_category" name="D1"><font color = "red"><b>CAT 1</b></font></label>
						</div>
					</div>
					<div class="form-group">
						<label for="new_nature" class="col-sm-4 control-label">Nature of Incident</label>
						<div class="col-sm-8">
							<label class="form-control" id="new_nature" name="D2">${nature}</label>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_date" class="col-sm-4 control-label">Estimated Start Date</label>
						<div class="col-sm-8">
							<label class="form-control" id="new_estimated_start_date">${start_date}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="new_estimated_start_time" class="col-sm-4 control-label">Estimated Start Time</label>
						<div class="col-sm-8">
							<label class="form-control" id="new_estimated_start_time">${start_time}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_location" class="col-sm-4 control-label">Incident Location</label>
						<div class="col-sm-8">
							<label class="form-control" id="new_call_location">${call_location}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="new_incident_coordinates" class="col-sm-4 control-label">Incident Coordinates</label>
						<div class="col-sm-3">
							<label class="form-control" id="new_incident_coordinates_north">${north_coordi}</label>
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">Latitude</div>
						<div class="col-sm-3">
							<label class="form-control" id="new_incident_coordinates_east">${east_coordi}</label>
						</div>
						<div class="col-sm-1 entry-placeholder" style="margin-top: 7px;">Longitude</div>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-8 col-sm-offset-2"><hr></div>
					</div>
					<div class="form-group" style="padding:0 15px;">
						<div class="col-sm-4 col-sm-offset-4">
							<button class="btn btn-secondary btn-block" type="submit">Crisis Verified</button>
						</div>
					</div>
                        </td>
-->
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
</body>
</html>