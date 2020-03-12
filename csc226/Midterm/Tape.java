import java.util.*;

public class Tape {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int tapes = in.nextInt();
    while (tapes != 0) {
      if (tapes == 1) {
        System.out.println(0);
        in.nextInt();
        tapes = in.nextInt();
      } else {
        long tot_sum = 0;
        long[] tapeArray = new long[tapes];
        for (int i = 0; i < tapes; i++) {
          tapeArray[i] = in.nextLong();
        }
        while(tapeArray.length > 1) {
          long sum = tapeArray[0] + tapeArray[1];
          tot_sum += sum;
          long[] newArray = new long[tapeArray.length - 1];
          for (int i = 2; i < tapeArray.length; i++) {
            newArray[i - 2] = tapeArray[i];
          }
          newArray[newArray.length - 1] = sum;
          Arrays.sort(newArray);
          tapeArray = newArray;
        }
        System.out.println(tot_sum - tapes + 1);
        tapes = in.nextInt();
      }
    }
  }
}
