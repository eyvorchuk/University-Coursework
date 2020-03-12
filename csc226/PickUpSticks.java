// V00864667 Yvorchuk, Eric

import java.util.*;
import java.io.*;

public class PickUpSticks {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sticks = in.nextInt();
		int pairs = in.nextInt();
		Digraph sticksGraph = new Digraph(sticks);
		for (int i = 1; i <= pairs; i++) {
		    sticksGraph.addEdge(in.nextInt(), in.nextInt());
		}
		System.out.println(sticksGraph.toString());
	}
}	