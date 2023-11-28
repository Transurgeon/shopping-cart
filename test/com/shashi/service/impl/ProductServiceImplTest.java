package com.shashi.service.impl;

import com.shashi.beans.ProductBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;
    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    void addProduct() {
    }

    @Test
    void testAddProduct() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void updateProductPrice() {
    }

    @Test
    void testGetAllProducts() {
        List<ProductBean> products = productService.getAllProducts();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testGetAllProductsByType() {
        // Test data
        String type = "";

        List<ProductBean> products = productService.getAllProductsByType(type);

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testSearchAllProducts() {
        // Test data
        String search = "iphone";

        List<ProductBean> products = productService.searchAllProducts(search);

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testGetImage() {
        // Test data
        String prodId = "P20230423084144";

        byte[] image = productService.getImage(prodId);

        assertNotNull(image);
        assertTrue(image.length > 0);
    }

    @Test
    void testGetProductDetails() {
        // Test data
        String prodId = "P20230423084144";

        ProductBean product = productService.getProductDetails(prodId);

        assertNotNull(product);
        assertEquals(prodId, product.getProdId());
    }

    @Test
    void updateProductWithoutImage() {
    }

    @Test
    void testGetProductPrice() {
        // Test data
        String prodId = "P20230423084144";

        double price = productService.getProductPrice(prodId);

        assertTrue(price > 0);
    }

    @Test
    void testSellNProduct() {
        // Test data
        String prodId = "P20230423084160";
        int n = 2;

        boolean result = productService.sellNProduct(prodId, n);

        assertTrue(result);
    }

    @Test
    void testGetProductQuantity() {
        // Test data
        String prodId = "P20230423084160";

        int quantity = productService.getProductQuantity(prodId);

        assertTrue(quantity >= 0);
    }
}