// V00864667 Yvorchuk, Eric

import java.util.*;
import java.io.*;

public class Kastenlauf {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        for (int i = 1; i <= tests; i++) {
            int components = in.nextInt() + 2;
            WeightedQuickUnionUF unionize = new WeightedQuickUnionUF(components);
            int[] xCoords = new int[components];
            int[] yCoords = new int[components];
            for (int j = 0; j < components; j++) {
                xCoords[j] = in.nextInt();
                yCoords[j] = in.nextInt();
            }
            for (int j = 0; j < components; j++) {
                for (int k = 0; k < components; k++) {
                    if (Math.abs(xCoords[j] - xCoords[k]) + Math.abs(yCoords[j] - yCoords[k]) <= 1000) {
                        unionize.union(j,k);
                    }
                }
            }
            if (unionize.connected(0, components - 1)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }
}