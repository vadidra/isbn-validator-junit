package com.vadidra.learn.junit.isbnvalidator;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    final static String ISBN = "0140177396";
    final static Book BOOK = new Book(ISBN, "Of Mice and Men", "J. Steinbeck");

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @BeforeEach
    public void setup(){
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }


    @Test
    public void canGetCorrectLocatorCode(){

        when(testWebService.lookup(anyString())).thenReturn(BOOK);
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String locatorCode = stockManager.getLocatorCode(ISBN);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataPresentInDatabase(){

        when(testDatabaseService.lookup(ISBN)).thenReturn(BOOK);

        String locatorCode = stockManager.getLocatorCode(ISBN);

        verify(testDatabaseService).lookup(ISBN);
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataNotPresentInDatabase(){

        when(testDatabaseService.lookup(ISBN)).thenReturn(null);
        when(testWebService.lookup(ISBN)).thenReturn(BOOK);

        String locatorCode = stockManager.getLocatorCode(ISBN);

        verify(testDatabaseService).lookup(ISBN);
        verify(testWebService).lookup(ISBN);
    }
}
