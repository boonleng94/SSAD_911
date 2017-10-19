<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crisis Management System | Warning</title>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script>
			var message="${message}";
			var redirect="${redirect}";
			alert(message);
			window.location.href = redirect;
		</script>
	</head>
</html>