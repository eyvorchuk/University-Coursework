import java.util.*;

public class Natjecanje {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int teams = in.nextInt();
    int damaged = in.nextInt();
    boolean[] hasKayak = new boolean[teams];
    Arrays.fill(hasKayak,true);
    boolean[] hasReserve = new boolean[teams];
    int reserve = in.nextInt();
    for (int i = 0; i < damaged; i++) {
      hasKayak[in.nextInt() - 1] = false;
    }
    for (int i = 0; i < reserve; i++) {
      int team = in.nextInt() - 1;
      if(!hasKayak[team]) {
        hasKayak[team] = true;
      } else {
        hasKayak[team] = hasReserve[team] = true;
      }
    }
    for (int i = 1; i < teams - 1; i++) {
      if(!hasKayak[i] && (hasReserve[i - 1])) {
        hasKayak[i] = true;
        hasReserve[i - 1] = false;
      } else if (!hasKayak[i] && hasReserve[i + 1]) {
        hasKayak[i] = true;
        hasReserve[i + 1] = false;
      }
    }
    if (!hasKayak[0] && hasReserve[1]) {
      hasKayak[0] = true;
      hasReserve[1] = false;
    }
    if (!hasKayak[teams - 1] && hasReserve[teams - 2]) {
      hasKayak[teams - 1] = true;
      hasReserve[teams - 2] = false;
    }
    int unable = 0;
    for (int i = 0; i < teams; i++) {
      if (!hasKayak[i]) {
        unable++;
      }
    }
    System.out.println(unable);
  }
}
