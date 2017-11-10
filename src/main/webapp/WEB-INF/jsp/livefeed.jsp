<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="static/js/login.js"></script>

	<!-- jQuery -->
	<script src="static/js/jquery-3.2.1.min.js"></script>

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
		<div class="table-responsive">
			<div class="col-sm-12 text-center" style="margin-bottom: 20px">
				<h1>Live Feed</h1>
			</div>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td class="auto-style1">
							<div class="table-entry">
								<img src="http://10.27.143.205:6888/video/live.mjpg?id=23cca9f5-530a-e387-85ec-4d045fc7fcd5" height="350" width="100%" alt="live-feed-video" />
								<img src="http://10.27.143.205:6888/video/live.mjpg?id=23cca9f5-530a-e387-85ec-4d045fc7fcd5" height="350" width="100%" alt="live-feed-video" />
								<img src="http://10.27.143.205:6888/video/live.mjpg?id=23cca9f5-530a-e387-85ec-4d045fc7fcd5" height="350" width="100%" alt="live-feed-video" />								
								<img src="http://10.27.143.205:6888/video/live.mjpg?id=23cca9f5-530a-e387-85ec-4d045fc7fcd5" height="350" width="100%" alt="live-feed-video" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
