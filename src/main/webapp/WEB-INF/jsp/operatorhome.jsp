<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crisis Management System | Home</title>

		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script  src="static/js/login.js"></script>

		<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
		<link href="static/css/bootstrap.min.css" rel="stylesheet">
		<link href="static/css/style.css" rel="stylesheet">
		<link href="static/css/login.css" rel="stylesheet" >

	</head>

	<body>
		<div class="login-form">
			<h1>operatorhome ${userID}</h1>
			<form action="/login" method="POST">
				<div class="form-group ">
					<input type="text" class="form-control" placeholder="Username" id="username" name="username">
					<i class="fa fa-user"></i>
				</div>
				<div class="form-group log-status">
					<input type="password" class="form-control" placeholder="Password" id="password" name="password">
					<i class="fa fa-lock"></i>
				</div>
				<span class="alertlogin">Invalid Credentials</span>
				<button type="submit" class="log-btn" >Log in</button>
			</form>
		</div>
	</body>
</html>