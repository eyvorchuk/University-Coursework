/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 20th 2016
 * Filename: AsciiEagle.java
 * Details: CSC110 Assignment 1
*/

/* The class AsciiEagle contains a drawing 
 * of the American Eagle using keyboard symbols.
 * This class includes two separate methods for the
 * upper and lower eagle sections.
*/  
public class AsciiEagle { 
	public static void main(String[] args) {
		upper();
		lower();
	}
	
	/* Drawing of the first five lines of the eagle. */
	public static void upper() {
		System.out.println("                /");
		System.out.println("\\             /*/");
		System.out.println(" \\\\\\' ,      / //");
		System.out.println("  \\\\\\//    _/ //'");
		System.out.println("   \\_-//' /  //<'");
	}
	
	/* Drawing of the last six lines of the eagle. */
	public static void lower() {
		System.out.println("    \\*///  <//'");
		System.out.println("    /  >>  *\\\\\\`");
		System.out.println("   /,)-^>>  _\\`");
		System.out.println("   (/   \\\\ / \\\\\\");
		System.out.println("        //  //\\\\\\");
		System.out.println("       ((");
	}	          
}		   	