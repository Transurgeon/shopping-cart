package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderBeanTest {

    @Test
    public void testTransactionIdGetterSetter() {
        OrderBean orderBean = new OrderBean();
        String transactionId = "T123";
        orderBean.setTransactionId(transactionId);
        assertEquals(transactionId, orderBean.getTransactionId());
    }

    @Test
    public void testProductIdGetterSetter() {
        OrderBean orderBean = new OrderBean();
        String productId = "P456";
        orderBean.setProductId(productId);
        assertEquals(productId, orderBean.getProductId());
    }

    @Test
    public void testQuantityGetterSetter() {
        OrderBean orderBean = new OrderBean();
        int quantity = 5;
        orderBean.setQuantity(quantity);
        assertEquals(quantity, orderBean.getQuantity());
    }

    @Test
    public void testAmountGetterSetter() {
        OrderBean orderBean = new OrderBean();
        double amount = 100.0;
        orderBean.setAmount(amount);
        assertEquals(amount, orderBean.getAmount(), 0.001); // Using delta for double comparison
    }

    @Test
    public void testShippedGetterSetter() {
        OrderBean orderBean = new OrderBean();
        int shipped = 1;
        orderBean.setShipped(shipped);
        assertEquals(shipped, orderBean.getShipped());
    }

    @Test
    public void testConstructorWithoutShipped() {
        String transactionId = "T123";
        String productId = "P456";
        int quantity = 5;
        double amount = 100.0;

        OrderBean orderBean = new OrderBean(transactionId, productId, quantity, amount);

        assertEquals(transactionId, orderBean.getTransactionId());
        assertEquals(productId, orderBean.getProductId());
        assertEquals(quantity, orderBean.getQuantity());
        assertEquals(amount, orderBean.getAmount(), 0.001);
        assertEquals(0, orderBean.getShipped());
    }

    @Test
    public void testFullConstructor() {
        String transactionId = "T123";
        String productId = "P456";
        int quantity = 5;
        double amount = 100.0;
        int shipped = 1;

        OrderBean orderBean = new OrderBean(transactionId, productId, quantity, amount, shipped);

        assertEquals(transactionId, orderBean.getTransactionId());
        assertEquals(productId, orderBean.getProductId());
        assertEquals(quantity, orderBean.getQuantity());
        assertEquals(amount, orderBean.getAmount(), 0.001);
        assertEquals(shipped, orderBean.getShipped());
    }

    @Test
    public void testDefaultConstructor() {
        OrderBean orderBean = new OrderBean();

        assertNull(orderBean.getTransactionId());
        assertNull(orderBean.getProductId());
        assergit adtEquals(0, orderBean.getQuantity());
        assertEquals(0.0, orderBean.getAmount(), 0.001);
        assertEquals(0, orderBean.getShipped());
    }
}
