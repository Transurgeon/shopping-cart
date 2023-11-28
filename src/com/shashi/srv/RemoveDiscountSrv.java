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

@WebServlet("/RemoveDiscountSrv")
public class RemoveDiscountSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveDiscountSrv() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("usertype");
		String userName = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");

		if (userType == null || !userType.equals("company")) {

			response.sendRedirect("login.jsp?message=Access Denied, Login As Admin!!");
			return;

		}

		else if (userName == null || password == null) {

			response.sendRedirect("login.jsp?message=Session Expired, Login Again!!");
			return;
		}

		// login checked

		String prodId = request.getParameter("prodid");

		SellerServiceImpl sellerService = new SellerServiceImpl();

		String status = sellerService.removeExistingDiscount(prodId);

		RequestDispatcher rd = request.getRequestDispatcher("discount_suggestions.jsp?message=" + status);

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
