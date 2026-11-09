
import java.util.*;
import java.util.concurrent.*;

class StudentResult implements Callable<String> {
    String name;
    int marks;

    StudentResult(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String call() {
        try {
            if (name == null || name.isEmpty())
                throw new Exception("Invalid name");

            if (marks < 0 || marks > 100)
                throw new Exception("Invalid marks");

            String result = marks >= 50 ? "PASS" : "FAIL";
            return name + " - " + marks + " - " + result;

        } catch (Exception e) {
            return name + " - Error: " + e.getMessage();
        }
    }
}

public class ResultApp {
    public static void main(String[] args) {

        // Manual input inside the code
        String[] names = {"Arun", "Priya", "Kumar", "Ravi", "Meena"};
        int[] marks = {85, 92, 45, 110, 78};

        ExecutorService pool =
                Executors.newFixedThreadPool(4);

        ConcurrentHashMap<Integer, String> results =
                new ConcurrentHashMap<>();

        List<Future<String>> futures = new ArrayList<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < names.length; i++) {
            final int id = i;
            StudentResult task =
                    new StudentResult(names[i], marks[i]);

            futures.add(pool.submit(() -> {
                String result = task.call();
                results.put(id, result);
                return result;
            }));
        }

        int success = 0, failure = 0;

        for (Future<String> f : futures) {
            try {
                String result = f.get();
                System.out.println(result);

                if (result.contains("Error"))
                    failure++;
                else
                    success++;

            } catch (Exception e) {
                failure++;
            }
        }

        pool.shutdown();

        long end = System.currentTimeMillis();

        System.out.println("\n--- SUMMARY ---");
        System.out.println("Total Students: " + names.length);
        System.out.println("Successful: " + success);
        System.out.println("Failed: " + failure);
        System.out.println("Processing Time: " + (end - start) + " ms");
    }
}
