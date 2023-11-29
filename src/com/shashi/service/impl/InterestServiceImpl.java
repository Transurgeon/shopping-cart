package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	private ProductBean getMostPopularProduct(List<ProductBean> products) {
		int mostUnitsSold = 0;
		ProductBean mostPopularProduct = null;
		for(ProductBean product : products) {
			if(product.getUnitSold() >= mostUnitsSold) {
				mostUnitsSold = product.getUnitSold();
				mostPopularProduct = product;
			}
		}
		return mostPopularProduct;
	}

	@Override
	public String applyDiscountOnInterests(String concordiaId) {
		SellerServiceImpl sellerService = new SellerServiceImpl();
		// get all interest of student
		List<InterestBean> interests = getAllStudentInterests(concordiaId);
		ProductServiceImpl productDao = new ProductServiceImpl();
		// get all product of one specific type
		for(InterestBean interest: interests) {
			List<ProductBean> interestProducts = productDao.getAllProductsByType(interest.getName());
			//choose most popular to add a random discount between 0-50%
			ProductBean mostPopularProduct = getMostPopularProduct(interestProducts);
			// query the change to put isDiscount + the percentage
			if(mostPopularProduct.isDiscounted()) continue; //already a discount dont touch it
			
			sellerService.setDiscountToProduct(mostPopularProduct.getProdId());
			Random random = new Random();
			int randomDiscount = random.nextInt(46) + 5;
			sellerService.addDiscountToProduct(mostPopularProduct.getProdId(), randomDiscount);
		}
		
		
		
	
		//add to a list
		// query the change to put isDiscount + the percentage
		
		return "success";
	}
	
	private ProductBean getRandomProduct(List<ProductBean> products) {
		Random random = new Random();
		int randomIndex = random.nextInt(products.size());
		return products.get(randomIndex);
	}

	@Override
	public void applyUsedOnInterests(String concordiaId) {
		// get all interest of student
		List<InterestBean> interests = getAllStudentInterests(concordiaId);
		ProductServiceImpl productDao = new ProductServiceImpl();
		// get all product of one specific type
		for(InterestBean interest: interests) {
			List<ProductBean> interestProducts = productDao.getAllProductsByType(interest.getName());
			//choose one random product to be used
			ProductBean usedProduct = getRandomProduct(interestProducts);
			// query the change to put isDiscount + the percentage
			if(usedProduct.isUsed()) continue; //already a used dont touch it
			
			//knock off the price?
			double usedPrice = usedProduct.getProdPrice();
			
			setUsedToProduct(usedProduct.getProdId(), (0.5 * usedPrice)); //set the product to used
			
			
			
		}
		
	}
	
	private void setUsedToProduct(String prodId, Double price) {
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		try { 
			ps = con.prepareStatement("update product set isUsed=?, pprice=? where pid=?");
			ps.setInt(1, 1);
			ps.setDouble(2, price);
			ps.setString(3, prodId);
			
			ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		
	}

}
