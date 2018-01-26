/* 
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 20th 2016
 * Filename: FloorArea.java
 * Details: CSC110 Assignment 1
*/ 

/*
 * The class FloorArea contains variables
 * and formulae to calculate the total area 
 * of a cathedral floor. This class includes
 * int variables for the dimensions and double
 * variables for the areas.
*/ 
public class FloorArea {
	public static void main(String[] args) {
		// declare variables and input given values
		int naveLength = 18;
		int chancelRadius = 18;
		int naveWidth = 85;
		int chapelRadiusSmall = 9;
		int chapelRadiusLarge = 16;
		double naveArea;
		double chancelArea;
		double chapelAreaSmall;
		double chapelAreaLarge;
		double totalArea;
		
		// calculate areas
		naveArea = (naveLength * naveWidth);
		chancelArea = Math.PI * Math.pow(chancelRadius/2,2)/2;
		chapelAreaSmall = Math.PI * Math.pow(chapelRadiusSmall/2,2);
		chapelAreaLarge = Math.PI * Math.pow(chapelRadiusLarge/2,2);
		
		// calculate total area
		totalArea = 2 * chapelAreaLarge + chapelAreaSmall
					+ chancelArea + naveArea;
		System.out.print("Total Area = ");
		System.out.println(totalArea);			
	}
}					
		