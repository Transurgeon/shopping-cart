<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
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
		<div class="row"
			style="margin-top: 5px; margin-left: 2px; margin-right: 2px;">
			
			<label for="userType">Select User Type:</label>
    			<select id="userType" class="col-md">
        			<option value="company">Company</option>
        			<option value="student" selected>Student</option>
    			</select>
    			
    		  <!-- Fields for Company Registration -->
    		<div id="companyFields" style="display: none;">
        		<form action="./RegisterSrv" method="post"
				class="col-md-6 col-md-offset-3"
				style="border: 1px grey; border-radius: 10px; background-color: #ffffff; padding: 25px;">
				<div style="font-weight: bold;" class="text-center">
					<h2 style="color: black;">Registration Form</h2>
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
				<div></div>
				<div class="row">
					<input type="hidden" id="userTypeHidden" name="userType" value="company">
					<div class="col-md-6 form-group">
						<label for="first_name">Company Name</label> <input type="text"
							name="companyName" class="form-control" id="first_name"
							name="first_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="first_name">Representative's Name</label> <input type="text"
							name="username" class="form-control" id="first_name"
							name="first_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="first_name">Email</label> <input type="email"
							name="email" class="form-control" id="first_name"
							name="first_name" required>
					</div>
				</div>
				<div class="form-group">
					<label for="last_name">Address</label>
					<textarea name="address" class="form-control" id="last_name"
						name="last_name" required></textarea>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label for="last_name">Phone number</label> <input type="number"
							name="mobile" class="form-control" id="last_name"
							name="last_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Pin Code</label> <input type="number"
							name="pincode" class="form-control" id="last_name"
							name="last_name" required>
					</div>

				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label for="last_name">Password</label> <input type="password"
							name="password" class="form-control" id="last_name"
							name="last_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Confirm Password</label> <input
							type="password" name="confirmPassword" class="form-control"
							id="last_name" name="last_name" required>
					</div>
				</div>
				<div class="row text-center">
					<div class="col-md-6" style="margin-bottom: 2px;">
						<button type="Reset" class="btn btn-danger">Reset</button>
					</div>
					<div class="col-md-6">
						<button type="submit" class="btn btn-success">Register</button>
					</div>
				</div>
			</form>
        	</div>
        	
        	<div id="studentFields">
			<form action="./RegisterSrv" method="post"
				class="col-md-6 col-md-offset-3"
				style="border: 1px grey; border-radius: 10px; background-color: #ffffff; padding: 25px;">
				<div style="font-weight: bold;" class="text-center">
					<h2 style="color: black;">Registration Form</h2>
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
				<div></div>
				<div class="row">
				<input type="hidden" id="userTypeHidden" name="userType" value="student">
					<div class="col-md-6 form-group">
						<label for="first_name">Name</label> <input type="text"
							name="username" class="form-control" id="first_name"
							name="first_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Email</label> <input type="email"
							name="email" class="form-control" id="last_name" name="last_name"
							required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">First Name</label> <input type="text"
							name="firstName" class="form-control" id="last_name" name="last_name"
							required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Last Name</label> <input type="text"
							name="lastName" class="form-control" id="last_name" name="last_name"
							required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Concordia Id</label> <input type="text"
							name="concordiaId" class="form-control" id="last_name" name="last_name"
							required>
					</div>
				</div>
				<div class="form-group">
					<label for="last_name">Address</label>
					<textarea name="address" class="form-control" id="last_name"
						name="last_name" required></textarea>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label for="last_name">Mobile</label> <input type="number"
							name="mobile" class="form-control" id="last_name"
							name="last_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Pin Code</label> <input type="number"
							name="pincode" class="form-control" id="last_name"
							name="last_name" required>
					</div>

				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label for="last_name">Password</label> <input type="password"
							name="password" class="form-control" id="last_name"
							name="last_name" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="last_name">Confirm Password</label> <input
							type="password" name="confirmPassword" class="form-control"
							id="last_name" name="last_name" required>
					</div>
				</div>
				<div class="row text-center">
					<div class="col-md-6" style="margin-bottom: 2px;">
						<button type="Reset" class="btn btn-danger">Reset</button>
					</div>
					<div class="col-md-6">
						<button type="submit" class="btn btn-success">Register</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			$('#userType').change(function(){
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