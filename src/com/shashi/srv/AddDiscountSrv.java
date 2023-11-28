package com.shashi.srv;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.shashi.service.impl.SellerServiceImpl;

/**
 * Servlet implementation class AddDiscountSrv
 */
@WebServlet("/AddDiscountSrv")
public class AddDiscountSrv  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public AddDiscountSrv() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("usertype");
		String userName = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String prodId = request.getParameter("prodid");
		int discountPercentage = Integer.parseInt(request.getParameter("discountPercentage"));
		
		

		if (userType == null || !userType.equals("company")) {

			response.sendRedirect("login.jsp?message=Access Denied!");
			return;

		}

		else if (userName == null || password == null) {

			response.sendRedirect("login.jsp?message=Session Expired, Login Again to Continue!");
			return;
		}
		
		//Get the product by his id
		SellerServiceImpl sellerService = new SellerServiceImpl();
		String status = sellerService.addDiscountToProduct(prodId, discountPercentage);
		
		//Apply the discount percentage 
		
		//Save the product in db
		
		//return to the discount page
		

		RequestDispatcher rd = request.getRequestDispatcher("discount_suggestions.jsp?message=" + status);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
