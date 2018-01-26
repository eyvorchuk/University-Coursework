/* 
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: September 22nd 2016
 * Filename: FloorAreaExtra.java
 * Details: CSC110 Assignment 1
*/ 

import java.util.*;

/* 
 * The class FloorAreaExtra contains a scanner
 * that allows users to simply input the dimensions of
 * the cathedral floor to calculate the total area.
*/ 
public class FloorAreaExtra {
    public static void main(String[] args) {
        Scanner cathedral = new Scanner(System.in);
		
        System.out.print("Nave Length : ");
        int naveLength = cathedral.nextInt();
        System.out.print("Nave Width : ");
        int naveWidth = cathedral.nextInt();
        System.out.print("Chancel Diameter : ");
        int chancelDiameter = cathedral.nextInt();
        System.out.print("Small Chapel Diameter : ");
        int chapelDiameterSmall = cathedral.nextInt();
        System.out.print("Large Chapel Diameter : ");
        int chapelDiameterLarge = cathedral.nextInt();
        System.out.println();
		
        double naveArea = (naveLength * naveWidth);
        double chancelArea = Math.PI * Math.pow(chancelDiameter/2.0,2)/2;
        double chapelAreaSmall = Math.PI * Math.pow(chapelDiameterSmall/2.0,2)/2;
        double chapelAreaLarge = Math.PI * Math.pow(chapelDiameterLarge/2.0,2)/2;
        double totalArea = 2 * chapelAreaLarge + chapelAreaSmall  
                           + chancelArea + naveArea;
        System.out.println("Total Area = " + (double) totalArea);
    }
}		
		
	