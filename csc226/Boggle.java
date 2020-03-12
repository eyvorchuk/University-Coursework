// V00864667 Yvorchuk, Eric

import java.util.Scanner;
import java.util.Arrays;

public class Boggle {
  static int count;
  static int points;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String[] words = new String[in.nextInt()];
    for (int i = 0; i < words.length; i++) {
      words[i] = in.next();
    }
    int boards = in.nextInt();
    for (int i = 1; i <= boards; i++) {
      count = 0;
      points = 0;
      String largest = "";
      char[][] letters = new char[6][6];
      for (int j = 1; j < 5; j++) {
        String line = in.next();
        letters[j][0] = letters[j][5] = ' ';
        for (int k = 1; k < 5; k++) {
          letters[j][k] = line.charAt(k - 1);
        }
      }
      for (int j = 0; j < 6; j++) {
        letters[0][j] = letters[5][j] = ' ';
      }
      for (int j = 0; j < words.length; j++) {
        String search = words[j];
        nestedLoop:
        for (int k = 1; k < 5; k++) {
          for (int l = 1; l < 5; l++) {
            if (letters[k][l] == search.charAt(0)) {
              if (search.length() == 1) {
                count++;
                break nestedLoop;
              }
              if (find(letters,k,l,search, 1)) {
                count++;
                points += getPoints(search);
                if (search.length() > largest.length() || (search.length() == largest.length() && search.compareTo(largest) < 0)) {
                  largest = search;
                }
                break nestedLoop;
              }
            }
          }
        }
      }
      System.out.println(points + " " + largest + " " + count);
    }
  }

  public static boolean find(char[][] letters, int row, int col, String search, int curr_length) {
    char orig_char = letters[row][col];
    letters[row][col] = ' ';
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (letters[i][j] == search.charAt(curr_length)) {
          if (curr_length + 1 == search.length()) {
            letters[row][col] = orig_char;
            return true;
          }
          if (find(letters,i,j,search,curr_length+1)) {
            letters[row][col] = orig_char;
            return true;
          }
        }
      }
    }
    letters[row][col] = orig_char;
    return false;
  }

  public static int getPoints(String search) {
    switch (search.length()) {
      case 3: return 1;
      case 4: return 1;
      case 5: return 2;
      case 6: return 3;
      case 7: return 5;
      case 8: return 11;
      default: return 0;
    }
  }
}
