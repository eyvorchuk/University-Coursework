/* 
 * The class AsciiEagleExtra contains methods
 * for the upper and lower eagle sections and for 
 * loops to make large spaces easier to manage.
*/
public class AsciiEagleExtra { 
    public static void main(String[] args) {
        upper();
        lower();
		
    }
	
    // Drawing of the first five lines of the eagle.
    public static void upper() {	
        for (int i = 1; i <= 16; i++) {
            System.out.print(" ");
        }
        System.out.println("/");	
        System.out.print("\\");
        for (int i = 1; i <= 13; i++) {
            System.out.print(" ");
        }
        System.out.println("/*/");
        System.out.print(" \\\\\\' ,");
        for (int i = 1; i <= 6; i++) {
            System.out.print(" ");
        }
        System.out.println("/ //");	
        System.out.print("  \\\\\\//"); 
        for (int i = 1;	i <= 4; i++) {
            System.out.print(" ");
        }	
        System.out.println("_/ //'");
        for (int i = 1; i <= 3; i++) {
            System.out.print(" ");
        }
        System.out.println("\\_-//' /  //<'");
    }
	
    // Drawing of the last six lines of the eagle.
    public static void lower() {
        for (int i = 1; i <= 4; i++) {
            System.out.print(" "); 
        }	
        System.out.println("\\*///  <//'"); 
        for (int i = 1; i <= 4; i++) {
            System.out.print(" "); 
        }
        System.out.println("/  >>  *\\\\\\`");
        for (int i = 1; i <= 3; i++) {
            System.out.print(" "); 
        }
        System.out.println("/,)-^>>  _\\`");
        for (int i = 1; i <= 3; i++) {
            System.out.print(" "); 
        }
        System.out.println("(/   \\\\ / \\\\\\");
        for (int i = 1; i <= 8; i++) {
            System.out.print(" ");
        }	
        System.out.println("//  //\\\\\\");
        for (int i = 1; i <= 7; i++) {
            System.out.print(" ");
        }
        System.out.println("((");
    }													
}
		

	
		
			
			
			