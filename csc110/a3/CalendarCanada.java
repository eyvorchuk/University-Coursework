import java.util.*;

/*
 * The class CalendarCanada prompts
 * the user to input the number of
 * days in the month and which day
 * the first Sunday is to output the
 * resulting calendar.
*/ 
public class CalendarCanada {
    public static void main(String[] args) {
        Scanner calendar = new Scanner(System.in);
        System.out.print("Number of days in month? ");
        int numDays = calendar.nextInt();
        System.out.print("Date of first Sunday? ");
        int startingSunday = calendar.nextInt();
        System.out.println("   Su    Mo    Tu    We    Th    Fr    Sa");      
        printSeparator();
        outputCalendar(numDays, startingSunday);
        printSeparator();		
    }
	
    /*
     * This method creates the top and bottom
     * lines of the calendar.
    */ 
    public static void printSeparator() {
        for (int i = 1; i <= 7; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }
    
    /* 
     * This method formats the calendar so that
     * each date is lined up properly.
    */ 
    public static String rightJustify(int startDay, int spaces) {
        System.out.print("|");
        String date = "" + startDay;
        for (int i = date.length(); i < spaces; i++) {
            date = " " + date;
        }
        return date;
    }
    
    /*
     * This method is used to output the calendar 
     * given the user's aforementioned inputs. It
     * uses if/else statements and for loops
     * to determine the number of blank days in the first row, 
     * the number of rows, and the number of
     * blank days in the last row.
     * input: number of days and date of first Sunday.
     * returns: resulting calendar.
    */ 
    public static void outputCalendar(int numDays, int startingSunday) {
    	int startDay = 1;
    	int blankDays;
    	int midRows;
    	if (startingSunday == 1) {
    	    blankDays = 0;
    	} else {
            blankDays = 8 - startingSunday;
    	}	   	        			
    	for (int i = 1; i <= blankDays; i++) {
    	    System.out.print("|     ");
    	}
    	for (int i = 1; i <= 7 - blankDays; i++) {
            System.out.print(rightJustify(startDay++, 4));
            System.out.print(" ");
    	}	
    	System.out.println("|");
    	if (numDays == 28 && startingSunday == 1) {
    	    midRows = 2;
    	} else if (numDays == 30 && startingSunday == 2) {
    	    midRows = 4;
    	} else if (numDays == 31 && startingSunday == 3) {
    	    midRows = 4;
    	} else if (numDays == 31 && startingSunday == 2) {
    	    midRows = 4;
    	} else {
    	    midRows = 3;
    	}                    
        for (int i = 1; i <= midRows; i++) {
     	    for (int j = 1; j <= 7; j++) {
    	        System.out.print(rightJustify(startDay++, 4));
    	        System.out.print(" ");
    	    }
    	    System.out.println("|");
    	} 
        for (int i = startDay; i <= numDays; i++) {
    	    System.out.print(rightJustify(startDay++, 4));
    	    System.out.print(" ");
    	}
    	if (numDays == 30 && startingSunday != 2) {
    	    for (int i = 1; i <= 5 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }     
    	    System.out.println("|");
    	} else if (numDays == 30 && startingSunday == 2) {
    	    for (int i = 1; i <= 12 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");
    	} else if (numDays == 31 && startingSunday == 3) {
    	    for (int i = 1; i <= 11 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");
    	} else if (numDays == 31 && startingSunday == 2) {
    	    for (int i = 1; i <= 11 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");                
    	} else if (numDays == 28 && startingSunday == 1) {
    	    System.out.println("|");
    	} else if (numDays == 28 && startingSunday != 1) {
    	    for (int i = 1; i <= 7 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");
    	} else if (numDays == 29) {
    	    for (int i = 1; i <= 6 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");    
    	} else {
    	    for (int i = 1; i <= 4 - blankDays; i++) {
    	        System.out.print("|     ");
    	    }
    	    System.out.println("|");
    	}                        
    }        	
}	
			
	
	