<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.shashi.service.impl.*, com.shashi.service.*,com.shashi.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Profile Details</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/changes.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body style="background-color: #f2f2f2;">

	<%
	/* Checking the user credentials */
	String userName = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	String concordiaId = (String)session.getAttribute("concordiaId");

	if (userName == null || password == null) {

		response.sendRedirect("login.jsp?message=Session Expired, Login Again!!");
	}

	UserService dao = new UserServiceImpl();
	StudentBean user = dao.getStudentDetails(userName, password);
	if (user == null)
		user = new StudentBean("Test User", 98765498765L, "test@gmail.com", "ABC colony, Patna, bihar", 87659, "lksdjf", "ced", "paradis", "12345678");
	%>



	<jsp:include page="header.jsp" />

	<div class="container bg-secondary">
		<h1 style="margin-bottom: 30px; position: -ms-device-fixed">My Profile</h1>
		<div class="row">
		
			<div class="col-lg-4">

				<div class="card mb-4 mb-lg-0">
					<div class="card-body p-0">


					</div>
				</div>
				<div class="card mb-4">
					<div class="card-body text-center">
						<img src="images/profile.jpg" class="rounded-circle img-fluid"
							style="width: 150px;">
							
						<h5 class="my-3">
						
<%--							Hello--%>
<%--							<%=user.getName()%>--%>
<%--							here!!--%>
						</h5>
						<!-- <p class="text-muted mb-1">Full Stack Developer</p>
						<p class="text-muted mb-4">Bay Area, San Francisco, CA</p> -->
					</div>
					<form action="./AddInterestsToStudentSrv" method="post">
					<label><h4>Choose interests </h4></label><br>
					<%	InterestServiceImpl interestDao = new InterestServiceImpl();
						List<InterestBean> interests = interestDao.getAllInterests();
						List<InterestBean> studentInterests = interestDao.getAllStudentInterests(concordiaId);
						boolean isChecked;
						for(InterestBean interest : interests){
							String interestName = interest.getName();
							isChecked = false;
							for(InterestBean studentInterest: studentInterests){
								if(studentInterest.getName().equalsIgnoreCase(interestName)){
									isChecked = true;
									break;
								}
							}
					%>		
					
					<input type="checkbox"  name="interests" value="<%= interestName %>" <%= isChecked ? "checked" : "" %>>
					<label ><%= interestName %></label><br>
					<%} %>
					<button type="submit">Add interests</button>
					</form>
				
				</div>


			</div>
			<div class="col-lg-8">
				<div class="card mb-4">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">UserName</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getName()%></p>
							</div>
						</div>
						<hr>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">First Name</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getFirstName()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Last Name</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getLastName()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Concordia Id</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getConcordiaId()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Email</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getEmail()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Phone</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getMobile()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Address</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getAddress()%>
								</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">PinCode</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0"><%=user.getPinCode()%>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>

	<%@ include file="footer.html"%>

</body>
</html>