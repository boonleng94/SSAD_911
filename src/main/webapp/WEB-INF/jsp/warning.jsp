<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crisis Management System | Error</title>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script>
			var errorMessage=$('#errorMessage').val();
			var errorRedirect=$('#errorRedirect').val();
			alert("Error: Please log in");
			window.location.href = "/";
		</script>
	</head>
	
	<body>
		<input type="hidden" id="errorMessage" value='${errorMessage}'/>
		<input type="hidden" id="errorRedirect" value='${errorRedirect}'/>
	</body>
</html>