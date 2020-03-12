// V00864667 Yvorchuk, Eric

import java.io.*;
import java.util.*;

public class KittenOnATree {
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    int catPos = in.nextInt();
	    int[] parents = new int[101];
	    in.nextLine();
	    int currParent = in.nextInt();
	    while (currParent != -1) {
	        String branch = in.nextLine();
	        Scanner readBranch = new Scanner(branch);
	        while (readBranch.hasNext()) {
	            parents[readBranch.nextInt()] = currParent;
	        }
	        currParent = in.nextInt();
	    }
	    int currPos = catPos;
	    while (currPos != 0) {
	        System.out.print(currPos + " ");
	        currPos = parents[currPos];
	    }
	}
}