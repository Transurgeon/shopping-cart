package com.shashi.service;

import java.util.List;

import com.shashi.beans.ProductBean;

public interface SellerService {

	public List<ProductBean> getAllProductsBySeller(String companyName);
	
	public List<ProductBean> selectProductsToDiscount(String companyName);
	
	public String addDiscountToProduct(String prodId,int percentage);
	
	public String removeExistingDiscount(String prodId);
	
	public List<ProductBean> getLowStockProducts(String companyName);
	
	public void setDiscountToProduct(String prodId);
}
