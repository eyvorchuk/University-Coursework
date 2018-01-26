import java.util.*;

/* 
 * The class CurrencyToWordsExtra uses
 * most of the same methods as 
 * CurrencyToWords, but rather than
 * take a money input from the user,
 * the money comes from Java's random
 * number generator for integers up to
 * ten thousand. The methods excluding the
 * main work exactly as they did in
 * CurrencyToWords.
*/  
public class CurrencyToWordsExtra {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Random rand = new Random();
            int value = rand.nextInt(10000);
            System.out.println("Monetary amount: " + value);
            String text = convertToWords(value);
            if (value < 1) {
                text = "zero ";
            } else if (value == 10000.00) {
                text = "ten thousand ";
            } else {
                text = convertToWords(value);
            }            
            System.out.print(text);
            int cents = 0;
            for (int j = text.length(); j <= 57; j++) {
                System.out.print("-"); 
            }
            System.out.println(" " + cents + "/100 dollars");
            System.out.println();
        }
    }
    
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
    
    public static String convertToWords(int value) {	
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