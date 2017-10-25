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
    <link href="static/css/login.css" rel="stylesheet"> 

     <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="static/js/bootstrap.min.js"></script>  

    <!-- Custom JavaScript <--></-->
    <script src="static/js/main.js"></script>
    
    <title>911 Call Center</title>
</head>

<body>
	<div class="container" style="position: absolute; top:0; bottom: 0; left: 0; right: 0; margin: auto; height: fit-content;">
		<h1 class="form-signin-heading text-center" style="font-size: 40px;">Crisis Management System</h1>
		<h2 class="form-signin-heading text-center" >911 Call Center</h2>
      <form class="form-signin" action="/login" method="POST" style="padding-top:0;">
        <br />
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus style="margin-bottom:10px;">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      </form>
    </div>
</body>

</html>
