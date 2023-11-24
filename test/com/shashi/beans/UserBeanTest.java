package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserBeanTest {

    @Test
    public void testDefaultConstructor() {
        UserBean userBean = new UserBean();
        assertNotNull(userBean);
        assertNull(userBean.getName());
        assertNull(userBean.getEmail());
        assertNull(userBean.getAddress());
        assertEquals(0, userBean.getPinCode());
        assertNull(userBean.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        String userName = "JohnDoe";
        Long mobileNo = 1234567890L;
        String emailId = "john.doe@example.com";
        String address = "123 Main Street";
        int pinCode = 12345;
        String password = "password123";

        UserBean userBean = new UserBean(userName, mobileNo, emailId, address, pinCode, password);
        assertNotNull(userBean);
        assertEquals(userName, userBean.getName());
        assertEquals(mobileNo, userBean.getMobile());
        assertEquals(emailId, userBean.getEmail());
        assertEquals(address, userBean.getAddress());
        assertEquals(pinCode, userBean.getPinCode());
        assertEquals(password, userBean.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        UserBean userBean = new UserBean();

        String userName = "JaneDoe";
        Long mobileNo = 9876543210L;
        String emailId = "jane.doe@example.com";
        String address = "456 Park Avenue";
        int pinCode = 54321;
        String password = "password456";

        userBean.setName(userName);
        userBean.setMobile(mobileNo);
        userBean.setEmail(emailId);
        userBean.setAddress(address);
        userBean.setPinCode(pinCode);
        userBean.setPassword(password);

        assertEquals(userName, userBean.getName());
        assertEquals(mobileNo, userBean.getMobile());
        assertEquals(emailId, userBean.getEmail());
        assertEquals(address, userBean.getAddress());
        assertEquals(pinCode, userBean.getPinCode());
        assertEquals(password, userBean.getPassword());
    }
}
