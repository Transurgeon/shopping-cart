package com.shashi.beans;


import java.io.Serializable;

@SuppressWarnings("serial")

public class SellerBean extends UserBean implements Serializable{
	
	private String companyName;
	
	//the list are going to be in seller service

	public SellerBean() {
	}

	public SellerBean(String userName, Long mobileNo, String emailId, String address, 
			int pinCode, String password, String companyName) {
		super();
		this.name = userName;
		this.mobile = mobileNo;
		this.email = emailId;
		this.address = address;
		this.pinCode = pinCode;
		this.password = password;
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	
}
