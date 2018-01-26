/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: October 20th 2016
 * Filename: CurrencyToWords.java
 * Details: CSC110 Assignment 4
*/

import java.util.*;

/* 
 * The class CurrencyToWords
 * converts an amount of money
 * inputted by the user into
 * words, with the cents as a
 * fraction over 100 at the end.
*/
 
public class CurrencyToWords {
    public static void main(String[] args) {
        double value = getValueFromUser();
        String text; convertToWords(value);
        if (value < 1) {
            text = "zero ";
        } else if (value == 10000.00) {
            text = "ten thousand ";
        } else {
            text = convertToWords(value);
        }            
        System.out.print(text);
        int cents = ((int)(value * 100.0 + 0.5)) % 100;
        if (cents >= 10) {
            for (int i = text.length(); i <= 56; i++) {
                System.out.print("-");
            }
        } else {
            for (int i = text.length(); i <= 57; i++) {
                System.out.print("-"); 
            }
        }
        System.out.println(" " + cents + "/100 dollars");               
    }
    
    /* 
     * This method is used to interact with the
     * user to obtain an amount of money and will
     * re-prompt them to re-input the money if they
     * do not input a numeric amount.
     * Input: Money
     * Return: Money
    */
    public static double getValueFromUser() {
        Scanner amount = new Scanner(System.in);
        System.out.print("What is the monetary amount? ");
        while (amount.hasNextDouble() == false) {
            System.out.print("Please re-enter the monetary amount using only numbers. ");
            String money = amount.nextLine();
        }
        double money = amount.nextDouble();
        return money;
    }
     
    /*
     * This method is the basis for creating the
     * word version of each digit. It is called
     * to by the following method after each 
     * digit place. Anomalies such as "teens"
     * and "tys" are dealt with whenever necessary.
    */  
    public static String baseCardinalToString(int value) {
        String digit;
        if (value == 1) {
            digit = "one";
        } else if (value == 2) {
            digit = "two";
        } else if (value == 3) {
            digit = "three";
        } else if (value == 4) {
            digit = "four";
        } else if (value == 5) {
            digit = "five";
        } else if (value == 6) {
            digit = "six";
        } else if (value == 7) {
            digit = "seven";
        } else if (value == 8) {
            digit = "eight";
        } else if (value == 9) {
            digit = "nine";
        } else if (value == 10) {
            digit = "ten";
        } else if (value == 11) {
            digit = "eleven";
        } else if (value == 12) {
            digit = "twelve";
        } else if (value == 13) {
            digit = "thirteen";
        } else if (value == 14) {
            digit = "fourteen";
        } else if (value == 15) {
            digit = "fifteen";
        } else if (value == 16) {
            digit = "sixteen";
        } else if (value == 17) {
            digit = "seventeen";
        } else if (value == 18) {
            digit = "eighteen";
        } else if (value == 19) {
            digit = "nineteen";
        } else if (value == 20) {
            digit = "twenty";
        } else if (value == 30) {
            digit = "thirty";
        } else if (value == 40) {
            digit = "forty";
        } else if (value == 50) {
            digit = "fifty";
        } else if (value == 60) {
            digit = "sixty";
        } else if (value == 70) {
            digit = "seventy";
        } else if (value == 80) {
            digit = "eighty";
        } else if (value == 90) {
            digit = "ninety"; 
        } else {
            digit = "";
        }
        return digit;
    }                                                                                                                 
    
    /*
     * This method takes the user's money value
     * and utilizes modulo and division 
     * arithmetic to separate each digit place.
     * It calls on the aforementioned method to
     * determine what the word version is of each
     * digit, or if it should be left blank.
    */ 
    public static String convertToWords(double value) {	
        String thousands = baseCardinalToString((int)(value % 10000 / 1000));
        String thousandsDigit;
        if (!"".equals(thousands) && value % 1000 != 0) {
            thousandsDigit = thousands + " thousand ";
        } else if (!"".equals(thousands) && value % 1000 == 0) {
            thousandsDigit = thousands + " thousand";
        } else {
            thousandsDigit = "";
        }    
        String hundreds = baseCardinalToString((int)(value % 1000 / 100));
        String hundredsDigit;
        if (!"".equals(hundreds) && value % 100 != 0) {
            hundredsDigit = hundreds + " hundred ";
        } else if (!"".equals(hundreds) && value % 100 == 0) {
            hundredsDigit = hundreds + " hundred";
        } else {
            hundredsDigit = "";    
        } 
        String and;
        if (value % 100 != 0 && value > 100) {
            and = "and ";
        } else {
            and = "";
        }                
        String teens = baseCardinalToString((int)(value % 100));
        String teensPlace;
        if (!"".equals(teens)) {
            teensPlace = teens;
        } else {
            teensPlace = "";
        }
        String tens = baseCardinalToString((int)((int) value % 100 / 10 * 10));
        String tensDigit;
        if (!"".equals(tens) && !"ten".equals(tens) && teensPlace == "") {
            tensDigit = tens;
        } else {
            tensDigit = "";
        }
        String hyphen;
        if (value % 10 != 0 && teensPlace == "") {
            hyphen = "-";
        } else {
            hyphen = "";
        }                                     
        String ones = baseCardinalToString((int)(value % 10));
        String onesDigit;
        if (!"".equals(ones) && teensPlace == "") {
            onesDigit = ones;
        } else {
            onesDigit = "";
        }
        String separator = " ";
        return thousandsDigit + hundredsDigit + and + teensPlace + tensDigit 
        + hyphen + onesDigit + separator;
    }                  
}                
