
import java.util.*;

public class JdbcBenchmark {

    static long statementInsert(int n) {
        List<String> data = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            data.add("INSERT INTO bus_pass VALUES (" + i + ")");
        }

        return System.nanoTime() - start;
    }

    static long preparedInsert(int n) {
        List<String> data = new ArrayList<>();

        String query = "INSERT INTO bus_pass VALUES (?)";

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            data.add(query + " " + i);
        }

        return System.nanoTime() - start;
    }

    static long batchInsert(int n) {
        List<String> batch = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            batch.add("P" + i);

            if (batch.size() == 1000) {
                batch.clear();
            }
        }

        batch.clear();

        return System.nanoTime() - start;
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 100000};

        System.out.println("Records\tStatement\tPrepared\tBatch");
        System.out.println("(Time in milliseconds)");

        for (int n : sizes) {

            long s = statementInsert(n) / 1000000;
            long p = preparedInsert(n) / 1000000;
            long b = batchInsert(n) / 1000000;

            System.out.println(n + "\t" + s + "\t\t"
                    + p + "\t\t" + b);
        }
    }
}
