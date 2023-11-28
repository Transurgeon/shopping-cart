<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.shashi.service.impl.*, com.shashi.service.*,com.shashi.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>View Products</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/changes.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f2f2f2;">

	<%
	/* Checking the user credentials */
	String userName = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	String userType = (String) session.getAttribute("usertype");
	String companyName = (String) session.getAttribute("companyName");

	String message = request.getParameter("message");
	if(message == null){
		message = "";
	}

	if (userType == null || !userType.equals("company")) {

		response.sendRedirect("login.jsp?message=Access Denied, Login as admin!!");

	}

	else if (userName == null || password == null) {

		response.sendRedirect("login.jsp?message=Session Expired, Login Again!!");

	}
	ProductServiceImpl prodDao = new ProductServiceImpl();
	SellerServiceImpl sellerServiceDao = new SellerServiceImpl();
	List<ProductBean> discountedProducts = new ArrayList<ProductBean>();

	String search = request.getParameter("search");
	String type = request.getParameter("type");
	String title = companyName + " Discount Suggestions List";
	if (search != null) {
		discountedProducts = prodDao.searchAllProducts(search);
		message = "Showing Results for '" + search + "'";
	} else if (type != null) {
		discountedProducts = prodDao.getAllProductsByType(type);
		message = "Showing Results for '" + type + "'";
	} else {
		discountedProducts = sellerServiceDao.selectProductsToDiscount(companyName);
	}
	if (discountedProducts.isEmpty()) {
		message = "No items found for the search '" + (search != null ? search : type) + "'";
		discountedProducts = prodDao.getAllProducts();
	}
	%>



	<jsp:include page="header.jsp" />
	<div class="text-center"
		style="color: black; font-size: 14px; font-weight: bold;"><%=title%></div>
	<div class="text-center"
		style="color: green; font-size: 14px; font-weight: bold;"><%=message%></div>
		
	<!-- Start of Product Items List -->
	<div class="container" style="background-color: #ffffff;">
		<div class="row text-center">

			<%
			for (ProductBean product : discountedProducts) {
			%>
			<div class="col-sm-4" style='height: 500px;'>
				<div class="thumbnail">
				<%if(product.getUnitSold() >= 19){ %>
				<p style="color: blue"> Popular </p>
				<%} %>
				<%if(product.getUnitSold() < 4){ %>
				<p style="color: red"> Low-selling </p>
				<%} %>
					<img src="./ShowImage?pid=<%=product.getProdId()%>" alt="Product"
						style="height: 150px; max-width: 180px; padding: 1%">
					<p class="productname"><%=product.getProdName()%>
						(
						<%=product.getProdId()%>
						)
					</p>
					<p class="productinfo"><%=product.getProdInfo()%></p>
					<p class="price">
						Rs
						<%=product.getProdPrice()%>
					</p>
					<p class="price">Units sold: <%=product.getUnitSold() %> </p>
					<form method="post">
						<button type="submit"
							formaction="./RemoveDiscountSrv?prodid=<%=product.getProdId()%>"
							class="btn btn-danger float-right">Remove existing discount</button>
						&nbsp;&nbsp;&nbsp;
					</form>
					<form method="post" action="./AddDiscountSrv">
						<label>Add a discount (%): </label>
						<input type="number" id="discountPercentage" name="discountPercentage">
						<input type="hidden" name="prodid" value="<%=product.getProdId()%>">
						<button type="submit" class="btn btn-success" >Add discount</button>
						&nbsp;&nbsp;&nbsp;
					</form>
				</div>
			</div>

			<%
			}
			%>

		</div>
	</div>
	<!-- ENd of Product Items List -->
	<%@ include file="footer.html"%>

</body>
</html>