
import java.io.*;
import java.nio.file.*;

public class SearchBenchmark {

    static long search(Path p, String id)
            throws IOException {

        long start = System.nanoTime();

        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith(id + ",")) {
                    break;
                }
            }
        }

        return (System.nanoTime() - start) / 1000000;
    }

    public static void main(String[] args) {

        try {
            int sizes[] = {1000, 10000, 50000, 100000};

            System.out.println("Records\tSearch Time (ms)");

            for (int size : sizes) {

                Path p = Files.createTempFile("search", ".csv");

                try (BufferedWriter w = Files.newBufferedWriter(p)) {
                    for (int i = 1; i <= size; i++) {
                        w.write("P" + i + ",REG" + i
                                + ",Student" + i + ",12,4500");
                        w.newLine();
                    }
                }

                long time = search(p, "P" + size);

                System.out.println(size + "\t" + time);

                Files.deleteIfExists(p);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
