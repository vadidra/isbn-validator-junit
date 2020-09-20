package com.vadidra.learn.junit.isbnvalidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    @Test
    public void canGetCorrectLocatorCode(){

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
                //return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataPresentInDatabase(){

        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        String isbn = "0140177396";
        when(databaseService.lookup(isbn)).thenReturn(new Book(isbn, "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup(isbn);
        verify(webService, times(0)).lookup(isbn);
    }

    @Test
    public void webserviceIsUsedIfDataNotPresentInDatabase(){
        fail();
    }
}
