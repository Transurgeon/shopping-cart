<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.shashi.service.impl.*, com.shashi.service.*,com.shashi.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Concordia Shopping Cart</title>
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
	String concordiaId = (String) session.getAttribute("concordiaId");
	
	String message = request.getParameter("message");
	if(message == null) message = "";

	boolean isValidUser = true;

	if (userType == null || userName == null || password == null || !userType.equals("customer")) {

		isValidUser = false;
	}

	ProductServiceImpl prodDao = new ProductServiceImpl();
	List<ProductBean> products = new ArrayList<ProductBean>();

	String search = request.getParameter("search");
	String type = request.getParameter("type");
	String priceFrom = request.getParameter("priceFrom");
	String priceTo = request.getParameter("priceTo");
	String isDiscount = request.getParameter("discount");
	String isUsed = request.getParameter("used");
	String title = "All Products";
	String mostPopular = request.getParameter("mostPopular");
	String leastPopular = request.getParameter("leastPopular");
	String message = "All Products";
	if (mostPopular != null && mostPopular.equals("on")) {
		products = prodDao.getMostPopularProducts();
		message = "Showing most popular products of the shopping cart";
	}
	else if (leastPopular != null && leastPopular.equals("on")) {
		products = prodDao.getLeastPopularProducts();
		message = "Showing least popular products of the shopping cart";
	}
	else if ((priceFrom != null || priceTo != null) && (!priceFrom.isEmpty() && !priceTo.isEmpty())) {
		products = prodDao.getPriceFilteredProducts(priceFrom, priceTo);
		title = "Showing price filter";
	}
	else if (isDiscount != null || isUsed != null) {
		products = prodDao.getUsedDiscountedProducts(isDiscount, isUsed);
		title = "Showing discounted or used products";
	}
	else if (search != null) {
		products = prodDao.searchAllProducts(search);
		title = "Showing Results for '" + search + "'";
	}
	else if (type != null) {
		products = prodDao.getAllProductsByType(type);
		title = "Showing Results for '" + type + "'";
	}
	else {
		products = prodDao.getAllProducts();
	}
	if (products.isEmpty()) {
		title = "No items found for the search '" + (search != null ? search : type) + "'";
		products = prodDao.getAllProducts();
	}
%>



	<jsp:include page="header.jsp" />

	<div class="text-center"
		style="color: black; font-size: 14px; font-weight: bold;"><%=title%></div>
	<div class="text-center"
		style="color: green; font-size: 14px; font-weight: bold;"><%=message%></div>
	<!-- <script>document.getElementById('mycart').innerHTML='<i data-count="20" class="fa fa-shopping-cart fa-3x icon-white badge" style="background-color:#333;margin:0px;padding:0px; margin-top:5px;"></i>'</script>
 -->
	<!-- Start of Product Items List -->
	<div class="container" style="padding-top: 5%">
		<div class="row text-center">

			<%
			for (ProductBean product : products) {
				int cartQty = new CartServiceImpl().getCartItemCount(userName, product.getProdId());
			
			%>

			<div class="col-sm-4" style='height: 350px;'>
				<div class="thumbnail">
					<div style="width: 100%; position: relative; margin-top: 2px; margin-left: 2px;">
						<% if (product.getDiscountPercentage() > 0) { %>
						<div style="width: 70px; height: 31px; position: absolute; left: 0; background: #FF5454; border-radius: 8px;">
							<div style="position: absolute; left: 5px; top: 4px; color: white; font-size: 13px; font-family: Kumbh Sans; font-weight: 600; line-height: 15.54px;">
								OFF <%=product.getDiscountPercentage()%>% 
							</div>
						</div>
						<% } %>
						<% if (product.isUsed()) { %>
						<div style="width: 70px; height: 31px; position: absolute; right: 0; background: #454CF8; border-radius: 8px;">
							<div style="position: absolute; left: 17px; top: 7px; color: white; font-size: 13px; font-family: Kumbh Sans; font-weight: 600; line-height: 15.54px;">
								USED
							</div>
						</div>
						<% } %>
					</div>
					<img src="./ShowImage?pid=<%=product.getProdId()%>" alt="Product"
						style="height: 150px; max-width: 180px">
					<p class="productname"><%=product.getProdName()%>
					</p>
					<%
					String description = product.getProdInfo();
					description = description.substring(0, Math.min(description.length(), 100));
					%>
					<p class="productinfo"><%=description%>..
					</p>
					<p class="price">
						<%=product.getProdPrice()%> $
					</p>
					<p class="unitSold">
						units sold: <%=product.getUnitSold()%>
					</p>
					<form method="post">
						<%
						if (cartQty == 0) {
						%>
						<button type="submit"
							formaction="./AddtoCart?uid=<%=userName%>&pid=<%=product.getProdId()%>&pqty=1"
							class="btn btn-success">Add to Cart</button>
						&nbsp;&nbsp;&nbsp;
						<button type="submit"
							formaction="./AddtoCart?uid=<%=userName%>&pid=<%=product.getProdId()%>&pqty=1"
							class="btn btn-primary">Buy Now</button>
						<%
						} else {
						%>
						<button type="submit"
							formaction="./AddtoCart?uid=<%=userName%>&pid=<%=product.getProdId()%>&pqty=0"
							class="btn btn-danger">Remove From Cart</button>
						&nbsp;&nbsp;&nbsp;
						<button type="submit" formaction="cartDetails.jsp"
							class="btn btn-success">Checkout</button>
						<%
						}
						%>
					</form>
					<br />
				</div>
			</div>

			<%
			}
			%>

		</div>
	</div>

	<%@ include file="footer.html"%>

</body>
</html>