package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void matches() {
        assertTrue(new Date().matches("01-01-2020"));
        assertFalse(new Date().matches("date"));
    }
}