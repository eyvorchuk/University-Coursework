/*
 * Name: Eric Yvorchuk
 * ID: V00864667
 * Date: December 1st 2016
 * Filename: TransposeSong.java
 * Details: CSC110 Assignment 7
*/

import java.util.*;
import java.io.*;

/*
 * The class TransposeSong takes
 * a song in the form of a text
 * file, transposes said song
 * by the given number of semitones,
 * and outputs the transposition to a
 * different file. All three components
 * are given as command line arguments.
 * To fit this purpose, a voice class and
 * a note class have been implemented.
*/
public class TransposeSong {
    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException ex) {
            System.out.println("The file does not exist in this directory.");
            System.exit(-1);
        }
        PrintStream ps = null;
        try {
            ps = new PrintStream(new File(args[1]));
        } catch (IOException ioe) {
            System.out.println("Error in writing to file.");
            System.exit(-1);
        }
        int semitones = Integer.parseInt(args[2]);
        String tempo = input.nextLine();
        String voice = input.nextLine();
        String title = input.nextLine();
        ps.println(tempo);
        ps.println(voice);
        ps.println(title);
        while(input.hasNext()) {
            input.nextLine();
            ps.println();
            Voice v = new Voice(input);
            v.transpose(semitones);
            v.toStream(ps);
        }
        ps.close();
    }
}