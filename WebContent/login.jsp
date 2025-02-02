<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/changes.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f2f2f2;">

	<%@ include file="header.jsp"%>

	<%
	String message = request.getParameter("message");
	%>
	<div class="container">
		<div class="row">
					<div class="col-md-12 form-group">
						<label for="userrole">Login As</label> <select name="usertype"
							id="userrole" class="col-md" required>
							<option value="student" selected>STUDENT</option>
							<option value="company">SELLER</option>
						</select>
					</div>
				</div>
			<div class="row"
			style="margin-top: 5px; margin-left: 2px; margin-right: 2px;">
			<div id="studentFields" >
			<form action="./LoginSrv" method="post"
				class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2"
				style=" border-radius: 10px; background-color: #ffffff; padding: 5%;">
				<div style="font-weight: bold;" class="text-center">
					<h2 style="color: black; font-family: 'Helvetica Neue', serif; font-weight: bold ">Login Form</h2>
					<%
					if (message != null) {
					%>
					<p style="color: blue;">
						<%=message%>
					</p>
					<%
					}
					%>
				</div>
				
				<div class="row">
				<input type="hidden" id="userTypeHidden" name="userType" value="student">
					<div class="col-md-12 form-group">
						<label for="last_name">Student email</label> <input type="email"
							placeholder="Enter Username" name="username" class="form-control"
							id="last_name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="last_name">Password</label> <input type="password"
							placeholder="Enter Password" name="password" class="form-control"
							id="last_name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="last_name">Concordia Id</label> <input type="text"
							placeholder="Enter Concordia Id" name="concordiaId" class="form-control"
							id="last_name" required>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 text-center">
						<button type="submit" class="btn btn-success" >Login</button>
					</div>
				</div>
			</form>
			</div>
			<div id="companyFields" style="display: none;">
			<form action="./LoginSrv" method="post"
				class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2"
				style=" border-radius: 10px; background-color: #ffffff; padding: 5%;">
				<div style="font-weight: bold;" class="text-center">
					<h2 style="color: black; font-family: 'Helvetica Neue', serif; font-weight: bold ">Login Form</h2>
					<%
					if (message != null) {
					%>
					<p style="color: blue;">
						<%=message%>
					</p>
					<%
					}
					%>
				</div>
				<div class="row">
				<input type="hidden" id="userTypeHidden" name="userType" value="company">
					<div class="col-md-12 form-group">
						<label for="last_name">Company Email</label> <input type="email"
							placeholder="Enter Username" name="username" class="form-control"
							id="last_name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="last_name">Password</label> <input type="password"
							placeholder="Enter Password" name="password" class="form-control"
							id="last_name" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="last_name">Company Name</label> <input type="text"
							placeholder="Enter Concordia Id" name="companyName" class="form-control"
							id="last_name" required>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 text-center">
						<button type="submit" class="btn btn-success" >Login</button>
					</div>
				</div>
			</form>
			</div>

		</div>
	</div>
<script>
		$(document).ready(function(){
			$('#userrole').change(function(){
				var selectedType = $(this).val();
				
				if(selectedType == 'company'){
					$('#companyFields').show();
					$('#studentFields').hide();
				} else if(selectedType == 'student'){
					$('#companyFields').hide();
					$('#studentFields').show();
				}
			});
		});
	</script>
	<%@ include file="footer.html"%>

</body>
</html>