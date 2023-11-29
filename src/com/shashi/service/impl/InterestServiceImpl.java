package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.InterestBean;
import com.shashi.beans.ProductBean;
import com.shashi.service.InterestService;
import com.shashi.utility.DBUtil;

public class InterestServiceImpl implements InterestService {

	@Override
	public String addInterestToStudent(String concordiaId, String name) {
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		String status = "Interest could not be added";
			try {
				ps = con.prepareStatement("insert into student_interest values(?,?)");
				ps.setString(2, name);
				ps.setString(1, concordiaId);
				
				int k = ps.executeUpdate();

				if (k > 0) {

					status = "Interest Added Successfully!";

				} else {

					status = "Interest Updation Failed!";
				}



			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		return status;
	}

	@Override
	public List<InterestBean> getAllStudentInterests(String concordiaId) {
		List<InterestBean> studentInterests = new ArrayList<InterestBean>();
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select interestId from student_interest where concordiaId=?");
			ps.setString(1,concordiaId);
			rs = ps.executeQuery();

			while (rs.next()) {
				InterestBean interestBean = new InterestBean();
				interestBean.setName(rs.getString(1));
				studentInterests.add(interestBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return studentInterests;
	}

	@Override
	public List<InterestBean> getAllInterests() {
		List<InterestBean> interests = new ArrayList<InterestBean>();
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from interest");
		
			rs = ps.executeQuery();

			while (rs.next()) {
				InterestBean interestBean = new InterestBean();
				interestBean.setName(rs.getString(1));
				interests.add(interestBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		
		return interests;
	}

	@Override
	public boolean isInterestAlreadyChecked(String concordiaId, String name) {
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from student_interest where concordiaId=? AND interestId=?");
			ps.setString(1, concordiaId);
			ps.setString(2, name);
			rs = ps.executeQuery();

			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return false;
	}

}
