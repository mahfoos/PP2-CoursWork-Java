package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleMenuTest {


    @Test
    public void check() {
        assertTrue(ConsoleMenu.check("Ahamed"));
        assertFalse(ConsoleMenu.check("123"));
    }
}