/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 30th 2016
 * Filename: FutureValueExtra.java
 * Details: CSC 110 Assignment 2
*/

import java.util.Scanner;
import java.text.DecimalFormat;

/*
 * The class FutureValueExtra uses if/else
 * statements to print a comment on the user's
 * long-term investment at the end
 * of the program depending on the 
 * interest rate.
*/ 
public class FutureValueExtra {
    public static void main(String[] args) {
        Scanner investment = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat(".00");
        System.out.print("The starting amount: "); 
        double startAmount = investment.nextDouble();
        System.out.print("The interest rate in percent: ");
        int percent = investment.nextInt();
        System.out.print("The number of years: ");
        int years = investment.nextInt();
        System.out.println("Y $");
        for (int i = 0; i <= years; i++) {
            String formattedAmount = formatter.format(startAmount);
            System.out.println("" + i + " " + formattedAmount);
            startAmount = startAmount + startAmount * 0.01 * percent;
        }
        System.out.println();
        if (percent < 0) {
            System.out.println("The investment's value is decreasing.");
        }
        else if (percent < 1) {
            System.out.println("The investment's value is unchanged.");
        }    
        else if (percent <= 10) {
            System.out.println("The investment's value is slightly increasing.");
        }    
        else if (percent <= 25) {
            System.out.println("The investment's value is noticeably increasing.");
        }
        else if (percent <= 50) {
            System.out.println("The investment will be worth a lot.");
        }
        else {
            System.out.println("The investment's value is skyrocketing.");
        }                              
    }
}            
            