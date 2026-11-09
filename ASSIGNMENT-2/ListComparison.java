
import java.util.*;

public class ListComparison {

    static void test(int n) {
        ArrayList<Integer> a = new ArrayList<>();
        LinkedList<Integer> l = new LinkedList<>();

        long start, end;

        start = System.nanoTime();
        for (int i = 0; i < n; i++)
            a.add(i);
        end = System.nanoTime();
        System.out.println("ArrayList Add: " + (end-start));

        start = System.nanoTime();
        for (int i = 0; i < n; i++)
            l.add(i);
        end = System.nanoTime();
        System.out.println("LinkedList Add: " + (end-start));

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++)
            a.get(n / 2);
        end = System.nanoTime();
        System.out.println("ArrayList Access: " + (end-start));

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++)
            l.get(n / 2);
        end = System.nanoTime();
        System.out.println("LinkedList Access: " + (end-start));

        ArrayDeque<Integer> d = new ArrayDeque<>();

        start = System.nanoTime();
        for (int i = 0; i < n; i++)
            d.addLast(i);
        end = System.nanoTime();
        System.out.println("ArrayDeque Add: " + (end-start));
    }

    public static void main(String[] args) {
        test(1000);
        test(10000);
        test(50000);
    }
}
