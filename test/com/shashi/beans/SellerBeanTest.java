package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SellerBeanTest {

    @Test
    public void testDefaultConstructor() {
        SellerBean sellerBean = new SellerBean();
        assertNotNull(sellerBean);
        assertNull(sellerBean.getName());
        assertNull(sellerBean.getEmail());
        assertNull(sellerBean.getAddress());
        assertNull(sellerBean.getPassword());
        assertNull(sellerBean.getCompanyName());
    }

    @Test
    public void testParameterizedConstructor() {
        String userName = "JohnDoe";
        Long mobileNo = 1234567890L;
        String emailId = "john.doe@example.com";
        String address = "123 Main Street";
        int pinCode = 12345;
        String password = "password123";
        String companyName = "ABC Corp";

        SellerBean sellerBean = new SellerBean(
                userName, mobileNo, emailId, address, pinCode, password, companyName
        );

        assertNotNull(sellerBean);
        assertEquals(userName, sellerBean.getName());
        assertEquals(mobileNo, sellerBean.getMobile());
        assertEquals(emailId, sellerBean.getEmail());
        assertEquals(address, sellerBean.getAddress());
        assertEquals(pinCode, sellerBean.getPinCode());
        assertEquals(password, sellerBean.getPassword());
        assertEquals(companyName, sellerBean.getCompanyName());
    }

    @Test
    public void testGettersAndSetters() {
        SellerBean sellerBean = new SellerBean();

        String companyName = "XYZ Ltd";

        sellerBean.setCompanyName(companyName);

        assertNull(sellerBean.getName());
        assertNull(sellerBean.getEmail());
        assertNull(sellerBean.getAddress());
        assertNull(sellerBean.getPassword());
        assertEquals(companyName, sellerBean.getCompanyName());
    }
}
