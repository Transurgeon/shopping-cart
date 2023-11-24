package com.shashi.beans;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentBeanTest {

    @Test
    public void testDefaultConstructor() {
        StudentBean studentBean = new StudentBean();
        assertNotNull(studentBean);
        assertNull(studentBean.getName());
        assertNull(studentBean.getEmail());
        assertNull(studentBean.getAddress());
        assertNull(studentBean.getPassword());
        assertNull(studentBean.getFirstName());
        assertNull(studentBean.getLastName());
        assertNull(studentBean.getConcordiaId());
    }

    @Test
    public void testParameterizedConstructor() {
        String userName = "JohnDoe";
        Long mobileNo = 1234567890L;
        String emailId = "john.doe@example.com";
        String address = "123 Main Street";
        int pinCode = 12345;
        String password = "password123";
        String firstName = "John";
        String lastName = "Doe";
        String concordiaId = "C123456";

        StudentBean studentBean = new StudentBean(
                userName, mobileNo, emailId, address, pinCode, password, firstName, lastName, concordiaId
        );

        assertNotNull(studentBean);
        assertEquals(userName, studentBean.getName());
        assertEquals(mobileNo, studentBean.getMobile());
        assertEquals(emailId, studentBean.getEmail());
        assertEquals(address, studentBean.getAddress());
        assertEquals(pinCode, studentBean.getPinCode());
        assertEquals(password, studentBean.getPassword());
        assertEquals(firstName, studentBean.getFirstName());
        assertEquals(lastName, studentBean.getLastName());
        assertEquals(concordiaId, studentBean.getConcordiaId());
    }

    @Test
    public void testGettersAndSetters() {
        StudentBean studentBean = new StudentBean();

        String firstName = "Jane";
        String lastName = "Doe";
        String concordiaId = "C789012";

        studentBean.setFirstName(firstName);
        studentBean.setLastName(lastName);
        studentBean.setConcordiaId(concordiaId);

        assertNull(studentBean.getName());
        assertNull(studentBean.getEmail());
        assertNull(studentBean.getAddress());
        assertNull(studentBean.getPassword());
        assertEquals(firstName, studentBean.getFirstName());
        assertEquals(lastName, studentBean.getLastName());
        assertEquals(concordiaId, studentBean.getConcordiaId());
    }
}
