import java.util.*;

public class Zoo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    TreeMap<String,Integer> animals = new TreeMap<String,Integer>();
    int num_animals = in.nextInt();
    int num_list = 0;
    while(num_animals != 0) {
      num_list++;
      in.nextLine();
      for (int i = 1; i <= num_animals; i++) {
          String full_animal = in.nextLine();
          Scanner scanAnimal = new Scanner(full_animal);
          String curr_animal = "";
          while (scanAnimal.hasNext()) {
            curr_animal = scanAnimal.next();
          }
          if(!animals.containsKey(curr_animal.toLowerCase())) {
            animals.put(curr_animal.toLowerCase(),1);
          } else {
            int count = animals.get(curr_animal.toLowerCase());
            animals.replace(curr_animal.toLowerCase(),count,count+1);
          }
        }
        System.out.println("List " + num_list + ":");
        while(!animals.isEmpty()) {
          System.out.println(animals.firstKey() + " | " + animals.get(animals.firstKey()));
          animals.pollFirstEntry();
        }
      num_animals = in.nextInt();
      }
    }
}
