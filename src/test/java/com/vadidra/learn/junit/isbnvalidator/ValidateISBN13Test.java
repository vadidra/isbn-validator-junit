package com.vadidra.learn.junit.isbnvalidator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateISBN13Test {

    final static String VALID_ISBN_13 = "978-0966852868";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkValidISBN13() {

        ValidateISBN validator = new ValidateISBN();
        boolean result13 = validator.checkISBN13(VALID_ISBN_13);
        assertTrue(result13);
    }

    @Test
    void checkInvalidISBN13() {

        ValidateISBN validator = new ValidateISBN();
        boolean result13i = validator.checkISBN13("123-1234567890");
        assertFalse(result13i);
    }

}
