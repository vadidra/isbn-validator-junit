package com.vadidra.learn.junit.isbnvalidator;

public interface ExternalISBNDataService {
    public Book lookup(String isbn);
}
