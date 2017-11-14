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
<!--
					<tr>
						<td class="auto-style1"> 
						<div align="center"> <!--The URL TO REPLACE IS HERE-->
							<img src="http://10.27.143.205:6888/video/live.mjpg?id=5af3dacc-a1d1-ffc6-b0fb-43a24c9e8bba" height="400" width="70%" alt="live-feed-video" />
						</div></td>
					</tr>
-->
					
					<tr>
						<td class="auto-style1"> <!--The URL TO REPLACE IS HERE-->
							<img src="http://10.27.143.205:6888/video/live.mjpg?id=188bb701-9db4-1a88-df9a-2cb9f45568ea" height="350" width="100%" alt="boatquayattack"/>
						</td> 
						<td class="auto-style1">
							<img src="static/images/sentosa.png" height="350" width="100%" alt="sentosaattack"/>
						</td>
					</tr>
					<tr style="background-color:#f9f9f9">
						<td class="auto-style1">
							<img src="static/images/orchard.png" height="350" width="100%" alt="orchardattack"/>								
						</td>
						<td class="auto-style1">
							<img src="static/images/boatquay.png" height="350" width="100%" alt="live-feed-video" />
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
