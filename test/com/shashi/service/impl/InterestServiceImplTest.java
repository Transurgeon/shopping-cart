package com.shashi.service.impl;

import com.shashi.beans.InterestBean;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterestServiceImplTest {
    @Test
    void addInterestToStudent() {
        InterestServiceImpl interestService = new InterestServiceImpl();
        String result = interestService.addInterestToStudent("123", "Programming");
        assertEquals("Interest could not be added", result);
    }

    @Test
    void getAllStudentInterests() {
        InterestServiceImpl interestService = new InterestServiceImpl();
        List<InterestBean> interests = interestService.getAllStudentInterests("123");

        assertEquals(0, interests.size());
    }

    @Test
    void getAllInterests() {
        InterestServiceImpl interestService = new InterestServiceImpl();
        List<InterestBean> interests = interestService.getAllInterests();

        // Assert the size of the list or specific elements
        assertEquals(9, interests.size()); // Adjust the expected size based on your actual data
        assertEquals("calculator", interests.get(0).getName());
    }

    @Test
    void isInterestAlreadyChecked() {
        InterestServiceImpl interestService = new InterestServiceImpl();
        boolean result = interestService.isInterestAlreadyChecked("123", "Programming");
        assertFalse(result);
    }

    @Test
    void applyDiscountOnInterests() {
        InterestServiceImpl interestService = new InterestServiceImpl();
        String result = interestService.applyDiscountOnInterests("123");
        assertEquals("success", result);
    }

    @Test
    void applyUsedOnInterests() {
    }
}