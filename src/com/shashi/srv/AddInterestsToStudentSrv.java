package com.shashi.srv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shashi.service.impl.InterestServiceImpl;

/**
 * Servlet implementation class AddDiscountSrv
 */
@WebServlet("/AddInterestsToStudentSrv")
public class AddInterestsToStudentSrv  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public AddInterestsToStudentSrv() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("usertype");
		String userName = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String concordiaId = (String) session.getAttribute("concordiaId");
		String[] selectedInterests = request.getParameterValues("interests");
	
		
		

		if (userType == null || !userType.equals("student")) {

			response.sendRedirect("login.jsp?message=Access Denied!");
			return;

		}

		else if (userName == null || password == null) {

			response.sendRedirect("login.jsp?message=Session Expired, Login Again to Continue!");
			return;
		}
		String status = "";
		// for each interest selected, link it to the student
		if(selectedInterests != null) {
			for(String interest : selectedInterests) {
				//check if it already is with the student
				
				InterestServiceImpl interestServiceImpl= new InterestServiceImpl();
				boolean isAlreadyChecked = interestServiceImpl.isInterestAlreadyChecked(concordiaId, interest);
				if(!isAlreadyChecked)
					status = interestServiceImpl.addInterestToStudent(concordiaId, interest);
			}
		}
		
		//determine discounts and used product based on user interest
		InterestServiceImpl interestService = new InterestServiceImpl();
		interestService.applyDiscountOnInterests(concordiaId);


		RequestDispatcher rd = request.getRequestDispatcher("userHome.jsp?message=" + status);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

