package com.shashi.service;

import java.util.List;

import com.shashi.beans.InterestBean;

public interface InterestService {

	public String addInterestToStudent(String concordiaId, String name);
	public List<InterestBean> getAllStudentInterests(String concordiaId);
	public List<InterestBean> getAllInterests();
	public boolean isInterestAlreadyChecked(String concordiaId, String name);
}
