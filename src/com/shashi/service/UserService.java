package com.shashi.service;

import com.shashi.beans.SellerBean;
import com.shashi.beans.StudentBean;
import com.shashi.beans.UserBean;

public interface UserService {

	/*
	 * private String userName; private Long mobileNo; private String emailId;
	 * private String address; private int pinCode; private String password;
	 */

	public String registerStudentUser(String userName, Long mobileNo, String emailId, String address, int pinCode,
			String password, String firstName, String lastName, String concordiaID);

	public String registerStudentUser(StudentBean user);
	
	public String registerSellerUser(SellerBean user);

	public boolean isRegistered(String emailId, String concordiaId);
	
	public boolean isRegisteredSeller(String emailId, String companyName);

	public String isValidCredential(String emailId, String password, String specificId, String userType);

	public StudentBean getStudentDetails(String emailId, String password);

	public String getFName(String emailId);

	public String getUserAddr(String userId);

	public boolean isValidConcordiaID(String concordiaID);

	public boolean isValidConcordiaEmail(String emailId);

}
