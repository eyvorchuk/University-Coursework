import java.util.Scanner;

public class TestScanner {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String domain = in.nextLine();
    System.out.println(domain);
    Scanner scanDom = new Scanner(domain);
    System.out.println(scanDom.next());
    scanDom = new Scanner(in.nextLine());
    System.out.println(scanDom.next());
  }
}
