package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderBeanTest {

    @Test
    public void testDefaultConstructor() {
        OrderBean orderBean = new OrderBean();
        assertNotNull(orderBean);
        assertNull(orderBean.getTransactionId());
        assertNull(orderBean.getProductId());
        assertEquals(0, orderBean.getQuantity());
        assertNull(orderBean.getAmount());
        assertEquals(0, orderBean.getShipped());
        assertNull(orderBean.getSellerId());
        assertNull(orderBean.getStudentId());
        assertNull(orderBean.getStatus());
    }

    @Test
    public void testParameterizedConstructorWithoutShipped() {
        String transactionId = "T123";
        String productId = "P456";
        int quantity = 3;
        Double amount = 100.0;
        String sellerId = "S789";
        String studentId = "U456";
        String status = "Pending";

        OrderBean orderBean = new OrderBean(transactionId, productId, quantity, amount, sellerId, studentId, status);
        assertNotNull(orderBean);
        assertEquals(transactionId, orderBean.getTransactionId());
        assertEquals(productId, orderBean.getProductId());
        assertEquals(quantity, orderBean.getQuantity());
        assertEquals(amount, orderBean.getAmount());
        assertEquals(0, orderBean.getShipped());
        assertEquals(sellerId, orderBean.getSellerId());
        assertEquals(studentId, orderBean.getStudentId());
        assertEquals(status, orderBean.getStatus());
    }

    @Test
    public void testParameterizedConstructorWithShipped() {
        String transactionId = "T456";
        String productId = "P789";
        int quantity = 5;
        Double amount = 150.0;
        int shipped = 1;
        String sellerId = "S123";
        String studentId = "U789";
        String status = "Shipped";

        OrderBean orderBean = new OrderBean(transactionId, productId, quantity, amount, shipped, sellerId, studentId, status);
        assertNotNull(orderBean);
        assertEquals(transactionId, orderBean.getTransactionId());
        assertEquals(productId, orderBean.getProductId());
        assertEquals(quantity, orderBean.getQuantity());
        assertEquals(amount, orderBean.getAmount());
        assertEquals(shipped, orderBean.getShipped());
        assertEquals(sellerId, orderBean.getSellerId());
        assertEquals(studentId, orderBean.getStudentId());
        assertEquals(status, orderBean.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        OrderBean orderBean = new OrderBean();

        String transactionId = "T789";
        String productId = "P012";
        int quantity = 2;
        Double amount = 75.0;
        int shipped = 0;
        String sellerId = "S456";
        String studentId = "U012";
        String status = "Processing";

        orderBean.setTransactionId(transactionId);
        orderBean.setProductId(productId);
        orderBean.setQuantity(quantity);
        orderBean.setAmount(amount);
        orderBean.setShipped(shipped);
        orderBean.setSellerId(sellerId);
        orderBean.setStudentId(studentId);
        orderBean.setStatus(status);

        assertEquals(transactionId, orderBean.getTransactionId());
        assertEquals(productId, orderBean.getProductId());
        assertEquals(quantity, orderBean.getQuantity());
        assertEquals(amount, orderBean.getAmount());
        assertEquals(shipped, orderBean.getShipped());
        assertEquals(sellerId, orderBean.getSellerId());
        assertEquals(studentId, orderBean.getStudentId());
        assertEquals(status, orderBean.getStatus());
    }
}
