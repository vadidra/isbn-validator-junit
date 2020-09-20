package com.vadidra.learn.junit.isbnvalidator;

public class ValidateISBN {

    public boolean checkISBN10(String isbn){

        // length must be 10
        int n = isbn.length();
        if (n != 10)
            return false;

        // Computing weighted sum
        // of first 9 digits
        int sum = 0;

        for (int i = 0; i < 10; i++){

            char c1 = isbn.charAt(i);
            System.out.println();
            if (!Character.isDigit(c1))
            {
                if(i == 9 && isbn.charAt(i) == 'X'){
                    sum += 10;
                }
                else {
                    throw new NumberFormatException();
                }
            }
            else {
                sum += Character.getNumericValue(c1) * (10 - i);
            }
        }

        return (sum % 11 == 0);

    }


    public boolean checkISBN13(String isbn){

        isbn = isbn.replace("-","");

        // length must be 13
        int n = isbn.length();
        if (n != 13)
            return false;

        int tot = 0;
        for ( int i = 0; i < 12; i++ )
        {
            int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
            tot += (i % 2 == 0) ? digit * 1 : digit * 3;
        }

        //checksum must be 0-9. If calculated as 10 then = 0
        int checksum = 10 - (tot % 10);
        if ( checksum == 10 )
        {
            checksum = 0;
        }

        return checksum == Integer.parseInt( isbn.substring( 12 ) );

    }
}
