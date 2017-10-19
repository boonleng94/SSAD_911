<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crisis Management System | Warning</title>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script>
			var errorMessage="${errorMessage}";
			var errorRedirect="${errorRedirect}";
			alert(errorMessage);
			window.location.href = errorRedirect;
		</script>
	</head>
</html>