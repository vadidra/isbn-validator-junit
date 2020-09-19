package com.vadidra.learn.junit.isbnvalidator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBN10Test {

    final static String VALID_ISBN_10 = "0966852869";
    final static String VALID_ISBN_10_2 = "043942089X";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkValidISBN10(){

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN10(VALID_ISBN_10);
        assertTrue(result);
    }

    @Test
    void checkValidISBN10WithX(){

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN10(VALID_ISBN_10_2);
        assertTrue(result);
    }

    @Test
    void checkInvalidISBN10(){

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN10("1234567890");
        assertFalse(result);
    }

    @Test
    void checkInvalidISBN10_9digits(){

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN10("123456789");
        assertFalse(result);
    }


    @Test
    void checkInvalidISBN10_characters(){

        ValidateISBN validator = new ValidateISBN();
        Executable executable = () -> validator.checkISBN10("helloworld");
        assertThrows(NumberFormatException.class, executable);
    }



}