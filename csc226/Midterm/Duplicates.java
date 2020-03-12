import java.util.*;

public class Duplicates {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    TreeSet<String> words = new TreeSet<String>();
    while(in.hasNext()) {
      String word = in.next();
      if(!words.contains(word)) {
        words.add(word);
      } else {
        System.out.println("no");
        return;
      }
    }
    System.out.println("yes");
  }
}
