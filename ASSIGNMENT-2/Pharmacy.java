
import java.util.*;

public class Pharmacy {

    public static void main(String[] args) {

        HashMap<String, Integer> hash = new HashMap<>();
        TreeMap<String, Integer> tree = new TreeMap<>();
        LinkedHashMap<String, Integer> linked =
                new LinkedHashMap<>();

        String[] codes = {"M103", "M101", "M105", "M102", "M104"};

        int[] stock = {50, 100, 30, 75, 60};

        for (int i = 0; i < codes.length; i++) {
            hash.put(codes[i], stock[i]);
            tree.put(codes[i], stock[i]);
            linked.put(codes[i], stock[i]);
        }

        System.out.println("HashMap: " + hash);
        System.out.println("TreeMap: " + tree);
        System.out.println("LinkedHashMap: " + linked);

        long start = System.nanoTime();

        for (int i = 0; i < 100000; i++)
            hash.get("M101");

        long end = System.nanoTime();

        System.out.println("Lookup Time: " + (end-start));

        System.out.println("Medicines in Code Order:");
        for (String key : tree.keySet())
            System.out.println(key + " : " + tree.get(key));

        System.out.println("Medicines in Insertion Order:");
        for (String key : linked.keySet())
            System.out.println(key + " : " + linked.get(key));
    }
}
