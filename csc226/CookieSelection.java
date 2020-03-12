// V00864667 Yvorchuk, Eric

import java.io.*;
import java.util.*;

public class CookieSelection {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PriorityQueue<Integer> smallCookies = new PriorityQueue<Integer>(1, Collections.reverseOrder());
        PriorityQueue<Integer> largeCookies = new PriorityQueue<Integer>();
        int totCookies = 0;
        while (in.hasNext()) {
            String diameter = in.next();
            if (!diameter.equals("#")) {
                totCookies++;
                if (largeCookies.size() == 0 || Integer.parseInt(diameter) > largeCookies.peek()) {
                    largeCookies.add(Integer.parseInt(diameter));
                } else {
                    smallCookies.add(Integer.parseInt(diameter));
                } 
                if (largeCookies.size() != totCookies - totCookies / 2) {
                    if (smallCookies.size() < largeCookies.size()) {
                        smallCookies.add(largeCookies.poll());
                    } else {
                        largeCookies.add(smallCookies.poll());
                    }
                }
            } else if (totCookies != 0) {
                System.out.println(largeCookies.poll());
                totCookies--;
                if (largeCookies.size() != totCookies - totCookies / 2) {
                    largeCookies.add(smallCookies.poll());
                }
            }
        }
    }
}                  