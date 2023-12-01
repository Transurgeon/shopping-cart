package com.shashi.service.impl;

import com.shashi.beans.ProductBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SellerServiceImplTest {

    @Test
    void getAllProductsBySeller() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
        List<ProductBean> products = sellerService.getAllProductsBySeller("huawei");

        // Add assertions based on your actual data
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void selectProductsToDiscount() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
        List<ProductBean> discountedProducts = sellerService.selectProductsToDiscount("apple");

        // Add assertions based on your actual data
        assertNotNull(discountedProducts);
    }

    @Test
    void addDiscountToProduct() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
        String result = sellerService.addDiscountToProduct("P20230423084161", 10);

        // Add assertions based on your expected results
        assertEquals("Discount could not be add to the product", result);
    }

    @Test
    void removeExistingDiscount() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
        String result = sellerService.removeExistingDiscount("P20230423084161");

        // Add assertions based on your expected results
        assertEquals("This product has no discount applied to it", result);
    }

    @Test
    void getLowStockProducts() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
        List<ProductBean> lowStockProducts = sellerService.getLowStockProducts("huawei");

        // Add assertions based on your actual data
        assertNotNull(lowStockProducts);
    }

    @Test
    void setDiscountToProduct() {
        SellerServiceImpl sellerService = new SellerServiceImpl();
    }
}
