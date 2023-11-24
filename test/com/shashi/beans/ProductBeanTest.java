package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProductBeanTest {

    @Test
    public void testDefaultConstructor() {
        ProductBean productBean = new ProductBean();
        assertNotNull(productBean);
        assertNull(productBean.getProdId());
        assertNull(productBean.getProdName());
        assertNull(productBean.getProdType());
        assertNull(productBean.getProdInfo());
        assertEquals(0.0, productBean.getProdPrice());
        assertEquals(0, productBean.getProdQuantity());
        assertNull(productBean.getProdImage());
        assertEquals(0.0, productBean.getDiscountPercentage());
        assertFalse(productBean.isDiscounted());
        assertFalse(productBean.isUsed());
        assertNull(productBean.getSeller());
    }

    @Test
    public void testParameterizedConstructor() {
        String prodId = "P123";
        String prodName = "Product1";
        String prodType = "Type1";
        String prodInfo = "Info1";
        double prodPrice = 50.0;
        int prodQuantity = 10;
        InputStream prodImage = new ByteArrayInputStream(new byte[]{});
        double discountPercentage = 10.0;
        boolean isDiscounted = true;
        boolean isUsed = false;
        String sellerId = "S456";

        ProductBean productBean = new ProductBean(
                prodId, prodName, prodType, prodInfo, prodPrice,
                prodQuantity, prodImage, discountPercentage, isDiscounted, isUsed, sellerId
        );

        assertNotNull(productBean);
        assertEquals(prodId, productBean.getProdId());
        assertEquals(prodName, productBean.getProdName());
        assertEquals(prodType, productBean.getProdType());
        assertEquals(prodInfo, productBean.getProdInfo());
        assertEquals(prodPrice, productBean.getProdPrice());
        assertEquals(prodQuantity, productBean.getProdQuantity());
        assertEquals(prodImage, productBean.getProdImage());
        assertEquals(discountPercentage, productBean.getDiscountPercentage());
        assertTrue(productBean.isDiscounted());
        assertFalse(productBean.isUsed());
        assertEquals(sellerId, productBean.getSeller());
    }

    @Test
    public void testGettersAndSetters() {
        ProductBean productBean = new ProductBean();

        String prodId = "P456";
        String prodName = "Product2";
        String prodType = "Type2";
        String prodInfo = "Info2";
        double prodPrice = 75.0;
        int prodQuantity = 5;
        InputStream prodImage = new ByteArrayInputStream(new byte[]{1, 2, 3});
        double discountPercentage = 15.0;
        boolean isDiscounted = false;
        boolean isUsed = true;
        String sellerId = "S789";

        productBean.setProdId(prodId);
        productBean.setProdName(prodName);
        productBean.setProdType(prodType);
        productBean.setProdInfo(prodInfo);
        productBean.setProdPrice(prodPrice);
        productBean.setProdQuantity(prodQuantity);
        productBean.setProdImage(prodImage);
        productBean.setDiscountPercentage(discountPercentage);
        productBean.setDiscounted(isDiscounted);
        productBean.setUsed(isUsed);
        productBean.setSeller(sellerId);

        assertEquals(prodId, productBean.getProdId());
        assertEquals(prodName, productBean.getProdName());
        assertEquals(prodType, productBean.getProdType());
        assertEquals(prodInfo, productBean.getProdInfo());
        assertEquals(prodPrice, productBean.getProdPrice());
        assertEquals(prodQuantity, productBean.getProdQuantity());
        assertEquals(prodImage, productBean.getProdImage());
        assertEquals(discountPercentage, productBean.getDiscountPercentage());
        assertFalse(productBean.isDiscounted());
        assertTrue(productBean.isUsed());
        assertEquals(sellerId, productBean.getSeller());
    }
}
