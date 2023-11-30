package com.shashi.service.impl;

import com.shashi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void registerStudentUser() {
    }

    @Test
    void testRegisterStudentUser() {
    }

    @Test
    void isRegistered() {
    }

    @Test
    void isValidCredential() {
    }

    @Test
    void getStudentDetails() {
    }

    @Test
    void getFName() {
    }

    @Test
    void getUserAddr() {
    }

    @Test
    void registerSellerUser() {
    }

    @Test
    void isRegisteredSeller() {
    }

    @Test
    public void testIsValidConcordiaID() {
        UserService userService = new UserServiceImpl();

        // Test case for a valid Concordia ID
        assertTrue(userService.isValidConcordiaID("40000000"));

        // Test case for an invalid Concordia ID (less than 8 characters)
        assertFalse(userService.isValidConcordiaID("1234567"));

        // Test case for an invalid Concordia ID (more than 8 characters)
        assertFalse(userService.isValidConcordiaID("400000001"));

        // Test case for an invalid Concordia ID (non-digit characters)
        assertFalse(userService.isValidConcordiaID("abcd1234"));

        // Test case for an invalid Concordia ID (does not start with 4)
        assertFalse(userService.isValidConcordiaID("30000000"));
    }

    @Test
    public void testIsValidConcordiaEmail() {
        UserService userService = new UserServiceImpl();

        // Test case for a valid Concordia email
        assertTrue(userService.isValidConcordiaEmail("testuser@concordia.ca"));

        // Test case for a valid Concordia email (live.concordia.ca)
        assertTrue(userService.isValidConcordiaEmail("testuser@live.concordia.ca"));

        // Test case for an invalid Concordia email (does not end with .ca)
        assertFalse(userService.isValidConcordiaEmail("testuser@concordia.com"));

        // Test case for an invalid Concordia email (non-Concordia domain)
        assertFalse(userService.isValidConcordiaEmail("testuser@gmail.com"));

        // Test case for an invalid Concordia email (missing @)
        assertFalse(userService.isValidConcordiaEmail("testuserconcordia.ca"));
    }
}