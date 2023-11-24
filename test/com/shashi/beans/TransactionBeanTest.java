package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

public class TransactionBeanTest {

    @Test
    public void testDefaultConstructor() {
        TransactionBean transactionBean = new TransactionBean();
        assertNotNull(transactionBean);
        assertNotNull(transactionBean.getTransactionId());
        assertNull(transactionBean.getUserName());
        assertNotNull(transactionBean.getTransDateTime());
        assertEquals(0.0, transactionBean.getTransAmount());
    }

    @Test
    public void testParameterizedConstructorWithoutTransactionId() {
        String userName = "JohnDoe";
        double transAmount = 50.0;

        TransactionBean transactionBean = new TransactionBean(userName, transAmount);
        assertNotNull(transactionBean);
        assertNotNull(transactionBean.getTransactionId());
        assertEquals(userName, transactionBean.getUserName());
        assertNotNull(transactionBean.getTransDateTime());
        assertEquals(transAmount, transactionBean.getTransAmount());
    }

    @Test
    public void testParameterizedConstructorWithTransactionId() {
        String transactionId = "T123";
        String userName = "JohnDoe";
        double transAmount = 75.0;

        TransactionBean transactionBean = new TransactionBean(transactionId, userName, transAmount);
        assertNotNull(transactionBean);
        assertEquals(transactionId, transactionBean.getTransactionId());
        assertEquals(userName, transactionBean.getUserName());
        assertNotNull(transactionBean.getTransDateTime());
        assertEquals(transAmount, transactionBean.getTransAmount());
    }

    @Test
    public void testParameterizedConstructorWithTimestamp() {
        String userName = "JohnDoe";
        Timestamp transDateTime = new Timestamp(System.currentTimeMillis());
        double transAmount = 100.0;

        TransactionBean transactionBean = new TransactionBean(userName, transDateTime, transAmount);
        assertNotNull(transactionBean);
        assertNotNull(transactionBean.getTransactionId());
        assertEquals(userName, transactionBean.getUserName());
        assertEquals(transDateTime, transactionBean.getTransDateTime());
        assertEquals(transAmount, transactionBean.getTransAmount());
    }

    @Test
    public void testParameterizedConstructorWithAllParameters() {
        String transactionId = "T456";
        String userName = "JohnDoe";
        Timestamp transDateTime = new Timestamp(System.currentTimeMillis());
        double transAmount = 125.0;

        TransactionBean transactionBean = new TransactionBean(transactionId, userName, transDateTime, transAmount);
        assertNotNull(transactionBean);
        assertEquals(transactionId, transactionBean.getTransactionId());
        assertEquals(userName, transactionBean.getUserName());
        assertEquals(transDateTime, transactionBean.getTransDateTime());
        assertEquals(transAmount, transactionBean.getTransAmount());
    }

    @Test
    public void testGettersAndSetters() {
        TransactionBean transactionBean = new TransactionBean();

        String transactionId = "T789";
        String userName = "JaneDoe";
        Timestamp transDateTime = new Timestamp(System.currentTimeMillis());
        double transAmount = 150.0;

        transactionBean.setTransactionId(transactionId);
        transactionBean.setUserName(userName);
        transactionBean.setTransDateTime(transDateTime);
        transactionBean.setTransAmount(transAmount);

        assertEquals(transactionId, transactionBean.getTransactionId());
        assertEquals(userName, transactionBean.getUserName());
        assertEquals(transDateTime, transactionBean.getTransDateTime());
        assertEquals(transAmount, transactionBean.getTransAmount());
    }
}
