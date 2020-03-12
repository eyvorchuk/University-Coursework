// V00864667 Yvorchuk, Eric

import java.util.*;

public class FunctionalFun {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      boolean injective = true;
      boolean surjective = true;
      boolean function = true;
      String domain = in.nextLine();
      Scanner scan_vals = new Scanner(domain);
      scan_vals.next();
      TreeMap<String,String> dom_mappings = new TreeMap<String,String>();
      while (scan_vals.hasNext()) {
        dom_mappings.put(scan_vals.next(),null);
      }
      String codomain = in.nextLine();
      scan_vals = new Scanner(codomain);
      scan_vals.next();
      HashMap<String,String> co_mappings = new HashMap<String,String>();
      while(scan_vals.hasNext()) {
        co_mappings.put(scan_vals.next(),null);
      }
      int num_mappings = in.nextInt();
      in.nextLine();
      if (num_mappings == 0) {
        System.out.println("injective");
      } else {
        int i;
        for (i = 1; i <= num_mappings; i++) {
          scan_vals = new Scanner(in.nextLine());
          String dom_value = scan_vals.next();
          scan_vals.next();
          String co_value = scan_vals.next();
          if (dom_mappings.get(dom_value) != null) {
            function = false;
            break;
          }
          dom_mappings.replace(dom_value,co_value);
          if (co_mappings.get(co_value) != null) {
            injective = false;
          }
          co_mappings.replace(co_value,dom_value);
        }
        if (co_mappings.containsValue(null)) {
          surjective = false;
        }
        while (i < num_mappings) {
          in.nextLine();
          i++;
        }
        if (!function) {
          System.out.println("not a function");
        } else if (injective && surjective) {
          System.out.println("bijective");
        } else if (injective && !surjective) {
          System.out.println("injective");
        } else if (surjective && !injective) {
          System.out.println("surjective");
        } else {
          System.out.println("neither injective nor surjective");
        }
      }
    }
  }
}
