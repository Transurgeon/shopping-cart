package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.ProductBean;
import com.shashi.service.SellerService;
import com.shashi.utility.DBUtil;

public class SellerServiceImpl implements SellerService {

	@Override
	public List<ProductBean> getAllProductsBySeller(String companyName) {
		List<ProductBean> products = new ArrayList<ProductBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from product where sellerId=?");
			ps.setString(1, companyName);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductBean product = new ProductBean();

				product.setProdId(rs.getString(1));
				product.setProdName(rs.getString(2));
				product.setProdType(rs.getString(3));
				product.setProdInfo(rs.getString(4));
				product.setProdPrice(rs.getDouble(5));
				product.setProdQuantity(rs.getInt(6));
				product.setProdImage(rs.getAsciiStream(7));
				product.setDiscountPercentage(rs.getDouble(8));
				product.setDiscounted(rs.getBoolean(9));
				product.setUsed(rs.getBoolean(10));
				product.setUnitSold(rs.getInt(12));
				product.setSeller(rs.getString(11));

				products.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return products;
	}

	@Override
	public List<ProductBean> selectProductsToDiscount(String companyName) {
		List<ProductBean> allProducts = new ArrayList<ProductBean>();
		List<ProductBean> discountedProducts = new ArrayList<ProductBean>();
		allProducts = getAllProductsBySeller(companyName);
		
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		for(ProductBean product : allProducts) {
			if(product.getUnitSold() >= 19 || product.getUnitSold() < 4) {
				discountedProducts.add(product);
			try {
				ps = con.prepareStatement("update product set isDiscounted=? where pid=?");
				ps.setInt(1, 1);
				ps.setString(2, product.getProdId());
				
				ps.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		return discountedProducts;
	}

	@Override
	public String addDiscountToProduct(String prodId,int percentage) {
		String status = "Discount could not be add to the product";
		ProductServiceImpl prodService = new ProductServiceImpl();
		ProductBean theProduct = prodService.getProductDetails(prodId);
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
	
		
		if(theProduct.isDiscounted()) {
			theProduct.setDiscountPercentage(percentage);
			theProduct.setProdPrice(setNewPrice(percentage, theProduct.getProdPrice()));
			prodService.updateProductPrice(prodId, theProduct.getProdPrice()); //update the price
			try { //update percentage in table
				ps = con.prepareStatement("update product set discountPercentage=? where pid=?");
				ps.setInt(1, percentage);
				ps.setString(2, prodId);
				
				ps.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
			}
			status = "Discount was succesfully applied to the product!!!";
		}
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
	
		return status;
	}
	
	private double setNewPrice(int percentage, double oldPrice) {
		double percentageInDecimal = (100-percentage) * 0.01;
		return oldPrice * percentageInDecimal;
	}
	
	private double setOldPrice(double percentage, double newPrice) {
		double percentageInDecimal = (100-percentage) * 0.01;
		double oldPrice = newPrice / percentageInDecimal;
		return oldPrice;
				
	}

	@Override
	public String removeExistingDiscount(String prodId) {
		String status = "This product has no discount applied to it";
		ProductServiceImpl prodService = new ProductServiceImpl();
		ProductBean theProduct = prodService.getProductDetails(prodId);
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		
		if(theProduct.isDiscounted() && theProduct.getDiscountPercentage() != 0) {
			//modify price and discount
			theProduct.setProdPrice(setOldPrice(theProduct.getDiscountPercentage(), theProduct.getProdPrice())); // price = oldprice * (100 - percentage)*0.01
			
			
			try { //update percentage in table
				ps = con.prepareStatement("update product set discountPercentage=?, pprice=? where pid=?");
				ps.setInt(1, 0);
				ps.setDouble(2, theProduct.getProdPrice());
				ps.setString(3, prodId);
				
				ps.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
			}
			status = "Discount was succesfully removed and old price was restored!";
		}
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		return status;
	}

	@Override
	public List<ProductBean> getLowStockProducts(String companyName) {
		List<ProductBean> allProducts = new ArrayList<ProductBean>();
		List<ProductBean> lowUnitsProducts = new ArrayList<ProductBean>();
		allProducts = getAllProductsBySeller(companyName);
	
		for(ProductBean product : allProducts) {
			if(product.getProdQuantity() < 3) {
				lowUnitsProducts.add(product);
			}
		}
		
		return lowUnitsProducts;
	}

	@Override
	public void setDiscountToProduct(String prodId) {
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		try { 
			ps = con.prepareStatement("update product set isDiscounted=? where pid=?");
			ps.setInt(1, 1);
			ps.setString(2, prodId);
			
			ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		
	}

}
