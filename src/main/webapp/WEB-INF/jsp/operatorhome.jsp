<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<html lang="en">

	<head>
		<title>Crisis Management System | Home</title>

		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<script src="static/js/jquery-3.2.1.min.js"></script>
		<script src="static/js/login.js"></script>

		<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
		<link href="static/css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="static/css/custom.css">
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
				<div class="col-sm-12 text-center" style="margin-bottom: 30px">
					<h1>Reports</h1>
					<div class="col-sm-6 col-sm-offset-3">
						<hr>
					</div>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Date & Time Created</th>
							<th>Date & Time Modified</th>
							<th>Nature of Incident</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="report" items="${OpsReportList}">
							<tr>
								<td>
									<div class="table-entry">${report.reportID}</div>
								</td>
								<td>
									<div class="table-entry">${report.date} ${report.callStartTime}</div>
								</td>
								<td>
									<div class="table-entry">${report.dateTimeModified}</div>
								</td>
								<td>
									<div class="table-entry">${report.incidentNature}</div>
								</td>
								<td>
									<div class="table-entry">${report.status}</div>
								</td>

								<td>
									<form action="/editReport" method="post">
										<button type="submit" class="btn btn-block btn-secondary" id="new_modify" name="reportID" value="${report.reportID}">Modify</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-sm-6 col-sm-offset-3">
				<hr>
			</div>

			<div class="col-sm-12">
				<div class="form-group form-group-sm">
					<div class="col-sm-4 col-sm-offset-4">
						<button type="button" class="btn btn-block btn-primary" id="new_submit" onclick="window.location.href='/newReport'">Create New Report</button>
					</div>
				</div>
			</div>
		</div>
	</body>

	</html>
