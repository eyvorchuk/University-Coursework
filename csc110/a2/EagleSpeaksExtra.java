/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 30th 2016
 * Filename: EagleSpeaksExtra.java
 * Details: CSC 110 Assignment 2
*/

import java.util.Scanner;

/*
 * The class EagleSpeaksExtra contains if/else 
 * statements to output the user's text in
 * all lowercase, uppercase, or as normal depending
 * on how long the message is.
*/
public class EagleSpeaksExtra {
    public static void main(String[] args) {
        speak();
    }
    
    /*
     * Modifies message case.
     * input: User's text
     * returns: the drawing of the eagle followed by
     * the inputted text which has its case modified
     * depending on its length.
    */
    public static void speak() { 
        System.out.print("What would you like the eagle to say? "); 
        Scanner eagle = new Scanner(System.in);
        String message = eagle.nextLine();
        printEagle();
        System.out.print("*");
        for (int i = 1; i <= message.length() + 2; i++) {
            System.out.print("-");
        }
        System.out.println("*");
        if (message.length() <= 5) {
            message = message.toLowerCase();
            System.out.println("| " + message + " |");
        }
        else if (message.length() >= 10) {
            message = message.toUpperCase();
            System.out.println("| " + message + " |"); 
        }
        else {          
            System.out.println("| " + message + " |");
        }    
        System.out.print("*");
        for (int i = 1; i <= message.length() + 2; i++) {
            System.out.print("-");
        }
        System.out.println("*");    
    }
    
    /*
     * The eagle drawing is displayed right after
     * the user types the message.
    */
    public static void printEagle() {
        System.out.println("    \\             /*/");
        System.out.println("     \\\\\\' ,      / //");
        System.out.println("      \\\\\\//    _/ //'");			
        System.out.println("       \\_-//' /  //<'");
        System.out.println("        \\*///  <//'");
        System.out.println("        /  >>  *\\\\\\`");
        System.out.println("       /,)-^>>  _\\`");
        System.out.println("       (/   \\\\ / \\\\\\");
        System.out.println("            //  //\\\\\\");
        System.out.println("      /    ((");
        System.out.println("     /");
    }
}    