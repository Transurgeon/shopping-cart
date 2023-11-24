package com.shashi.srv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.SellerBean;
import com.shashi.beans.StudentBean;
import com.shashi.beans.UserBean;
import com.shashi.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("userType").equalsIgnoreCase("student")) {
			System.out.println("inside student");
		
		response.setContentType("text/html");
		String userName = request.getParameter("username");
		Long mobileNo = Long.parseLong(request.getParameter("mobile"));
		String emailId = request.getParameter("email");
		String address = request.getParameter("address");
		int pinCode = Integer.parseInt(request.getParameter("pincode"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String concordiaId = request.getParameter("concordiaId");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String status = "";
		if (password != null && password.equals(confirmPassword)) {
			StudentBean user = new StudentBean(userName, mobileNo, emailId, address, pinCode, password,firstName,lastName,concordiaId );

			UserServiceImpl dao = new UserServiceImpl();

			status = dao.registerStudentUser(user);
		} else {
			status = "Password not matching!";
		}

		RequestDispatcher rd = request.getRequestDispatcher("register.jsp?message=" + status);

		rd.forward(request, response);
	}else if(request.getParameter("userType").equalsIgnoreCase("company")) {
		String companyName = request.getParameter("companyName");
		String username = request.getParameter("username");
		Long mobileNo = Long.parseLong(request.getParameter("mobile"));
		String emailId = request.getParameter("email");
		String address = request.getParameter("address");
		int pinCode = Integer.parseInt(request.getParameter("pincode"));
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String status = "";
		
		//confirm password
		if (password != null && password.equals(confirmPassword)) {
			SellerBean user = new SellerBean(username, mobileNo, emailId, address, pinCode, password, companyName);

			UserServiceImpl dao = new UserServiceImpl();

			status = dao.registerSellerUser(user);
		} else {
			status = "Password not matching!";
		}

		RequestDispatcher rd = request.getRequestDispatcher("register.jsp?message=" + status);

		rd.forward(request, response);
		
}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
