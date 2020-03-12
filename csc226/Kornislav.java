import java.util.*;

public class Kornislav {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int[] numbers = new int[4];
    for (int i = 0; i < 4; i++) {
      numbers[i] = in.nextInt();
    }
    Arrays.sort(numbers);
    System.out.println(numbers[0] * numbers[2]);
  }
}
