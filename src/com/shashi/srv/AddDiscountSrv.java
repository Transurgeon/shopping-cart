package com.shashi.srv;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.shashi.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class AddProductSrv
 */
@WebServlet("/AddDiscountSrv")
@MultipartConfig(maxFileSize = 16177215)
public class AddDiscountSrv  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("usertype");
		String userName = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String prodId = request.getParameter("prodid");
		String discountPercentage = request.getParameter("discountPercentage");
		
		

		if (userType == null || !userType.equals("admin")) {

			response.sendRedirect("login.jsp?message=Access Denied!");

		}

		else if (userName == null || password == null) {

			response.sendRedirect("login.jsp?message=Session Expired, Login Again to Continue!");
		}
		
		//Get the product by his id
		
		//Apply the discount percentage 
		
		//Save the product in db
		
		//return to the discount page
		
		String status = "Product Registration Failed!";
		String prodName = request.getParameter("name");
		String prodType = request.getParameter("type");
		String prodInfo = request.getParameter("info");
		double prodPrice = Double.parseDouble(request.getParameter("price"));
		int prodQuantity = Integer.parseInt(request.getParameter("quantity"));

		Part part = request.getPart("image");

		InputStream inputStream = part.getInputStream();

		InputStream prodImage = inputStream;

		ProductServiceImpl product = new ProductServiceImpl();

		status = product.addProduct(prodName, prodType, prodInfo, prodPrice, prodQuantity, prodImage, 1,false,false,""); //modify this line

		RequestDispatcher rd = request.getRequestDispatcher("addProduct.jsp?message=" + status);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
