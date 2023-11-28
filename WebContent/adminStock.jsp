<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.shashi.service.impl.*, com.shashi.service.*,com.shashi.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Stocks</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/changes.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
#specific-table.table-hover tbody tr:hover {
    background-color: black; /* Change this color to your preferred hover color */
}</style>
</head>
<body style="background-color: #f2f2f2;">
	<%
	/* Checking the user credentials */
	String userType = (String) session.getAttribute("usertype");
	String userName = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	String companyName = (String)session.getAttribute("companyName");

	if (userType == null || !userType.equals("company")) {

		response.sendRedirect("login.jsp?message=Access Denied, Login as admin!!");

	}

	else if (userName == null || password == null) {

		response.sendRedirect("login.jsp?message=Session Expired, Login Again!!");

	}
	%>

	<jsp:include page="header.jsp" />

	<div class="text-center"
		style="color: black; font-size: 24px; font-weight: bold; padding-bottom: 5px"><%=companyName.toUpperCase() %> Stock
		Products</div>
	<div class="text-center" id="warning" style="display:none; color: red; font-size: 24px; font-weight: bold; padding-bottom: 5px">WARNING: LOW STOCK SUGGESTION</div>
		
	<div class="container-fluid">
	<div class="col-md-12 form-group">
						<label for="stock">Stock options</label> <select name="stockType"
							id="stockType" class="col-md" required>
							<option value="allStock" selected>All stock</option>
							<option value="suggestedStock">Restocking suggestion</option>
						</select>
	</div>
		<div class="" id="normalstock">
			<table class="table table-hover table-sm">
				<thead
					style="background-color: #F55C5C; color: white; font-size: 18px;">
					<tr>
						<th>Image</th>
						<th>ProductId</th>
						<th>Name</th>
						<th>Type</th>
						<th>Price</th>
						<th>Sold Qty</th>
						<th>Stock Qty</th>
						<th colspan="2" style="text-align: center">Actions</th>
					</tr>
				</thead>
				<tbody style="background-color: white; font-size: 16px;">



					<%
					SellerServiceImpl productDao = new SellerServiceImpl();
					List<ProductBean> products = new ArrayList<ProductBean>();
					products = productDao.getAllProductsBySeller(companyName);
					for (ProductBean product : products) {
					%>

					<tr>
						<td><img src="./ShowImage?pid=<%=product.getProdId()%>"
							style="width: 50px; height: 50px;"></td>
						<td><a
							href="./updateProduct.jsp?prodid=<%=product.getProdId()%>"><%=product.getProdId()%></a></td>
						<%
						String name = product.getProdName();
						name = name.substring(0, Math.min(name.length(), 25)) + "..";
						%>
						<td><%=name%></td>
						<td><%=product.getProdType().toUpperCase()%></td>
						<td><%=product.getProdPrice()%></td>
						<td><%=new OrderServiceImpl().countSoldItem(product.getProdId())%></td>
						<td><%=product.getProdQuantity()%></td>
						<td>
							<form method="post">
								<button type="submit"
									formaction="updateProduct.jsp?prodid=<%=product.getProdId()%>"
									class="btn btn-primary">Update</button>
							</form>
						</td>
						<td>
							<form method="post">
								<button type="submit"
									formaction="./RemoveProductSrv?prodid=<%=product.getProdId()%>"
									class="btn btn-danger">Remove</button>
							</form>
						</td>

					</tr>

					<%
					}
					%>
					<%
					if (products.size() == 0) {
					%>
					<tr style="background-color: grey; color: white;">
						<td colspan="7" style="text-align: center;">No Items
							Available</td>

					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		
		<div class="" id="suggestedstock" style="display:none;">
			<table id="specific-table" class="table table-hover table-sm">
				<thead
					style="background-color: #F55C5C; color: white; font-size: 18px;">
					<tr>
						<th>Image</th>
						<th>ProductId</th>
						<th>Name</th>
						<th>Type</th>
						<th>Price</th>
						<th>Sold Qty</th>
						<th>Stock Qty</th>
						<th colspan="2" style="text-align: center">Actions</th>
					</tr>
				</thead>
				<tbody style="background-color: red; color: white; font-size: 16px;">



					<%
					SellerServiceImpl suggestionDao = new SellerServiceImpl();
					List<ProductBean> restockProducts = new ArrayList<ProductBean>();
					restockProducts = suggestionDao.getAllProductsBySeller(companyName);
					for (ProductBean product : restockProducts) {
					%>

					<tr>
						<td><img src="./ShowImage?pid=<%=product.getProdId()%>"
							style="width: 50px; height: 50px;"></td>
						<td><a
							href="./updateProduct.jsp?prodid=<%=product.getProdId()%>"><%=product.getProdId()%></a></td>
						<%
						String name = product.getProdName();
						name = name.substring(0, Math.min(name.length(), 25)) + "..";
						%>
						<td><%=name%></td>
						<td><%=product.getProdType().toUpperCase()%></td>
						<td><%=product.getProdPrice()%></td>
						<td><%=new OrderServiceImpl().countSoldItem(product.getProdId())%></td>
						<td><%=product.getProdQuantity()%></td>
						<td>
							<form method="post">
								<button type="submit"
									formaction="updateProduct.jsp?prodid=<%=product.getProdId()%>"
									class="btn btn-primary">Update</button>
							</form>
						</td>
						<td>
							<form method="post">
								<button type="submit"
									formaction="./RemoveProductSrv?prodid=<%=product.getProdId()%>"
									class="btn btn-danger">Remove</button>
							</form>
						</td>

					</tr>

					<%
					}
					%>
					<%
					if (products.size() == 0) {
					%>
					<tr style="background-color: grey; color: white;">
						<td colspan="7" style="text-align: center;">No Items
							Available</td>

					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
<script>
$(document).ready(function(){
			$('#stockType').change(function(){
				var selectedType = $(this).val();
				
				if(selectedType == 'allStock'){
					$('#normalstock').show();
					$('#suggestedstock').hide();
					$('#warning').hide();
				} else if(selectedType == 'suggestedStock'){
					$('#normalstock').hide();
					$('#suggestedstock').show();
					$('#warning').show();
				}
			});
		});
</script>
	<%@ include file="footer.html"%>
</body>
</html>