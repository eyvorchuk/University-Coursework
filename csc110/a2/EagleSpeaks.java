/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 29th 2016
 * Filename: EagleSpeaks.java
 * Details: CSC 110 Assignment 2
*/

import java.util.Scanner;

/*
 * The class EagleSpeaks contains a Scanner
 * which prompts the user to type a message
 * from the eagle, which is stored in a
 * text box that uses for loops to make 
 * it slightly longer than the message.
*/
public class EagleSpeaks {
    public static void main(String[] args) {
        speak();
    }
    
    /*
     * Prompts user to type what the eagle should say.
     * input: User's text
     * returns: the drawing of the eagle followed by
     * the inputted text stored in a text box.
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
        System.out.println("| " + message + " |");
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
        
    
                               