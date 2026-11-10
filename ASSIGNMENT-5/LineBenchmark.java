
import java.io.*;
import java.nio.file.*;

public class LineBenchmark {

    static void createFile(Path p, int sizeMB)
            throws IOException {

        byte[] data = new byte[1024 * 1024];

        for (int i = 0; i < data.length; i++) {
            data[i] = 'A';
        }

        try (OutputStream out = Files.newOutputStream(p)) {
            for (int i = 0; i < sizeMB; i++) {
                out.write(data);
            }
        }
    }

    static long unbuffered(Path p) throws IOException {
        long start = System.nanoTime();
        long count = 0;

        try (InputStream in = Files.newInputStream(p)) {
            while (in.read() != -1) {
                count++;
            }
        }

        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    static long buffered(Path p) throws IOException {
        long start = System.nanoTime();
        long count = 0;

        try (BufferedInputStream in =
                new BufferedInputStream(Files.newInputStream(p))) {

            byte[] buffer = new byte[8192];
            int n;

            while ((n = in.read(buffer)) != -1) {
                count += n;
            }
        }

        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    public static void main(String[] args) {

        try {
            int sizes[] = {1, 10, 50};

            System.out.println("Size\tUnbuffered(ms)\tBuffered(ms)");

            for (int size : sizes) {

                Path p = Files.createTempFile("test", ".dat");

                createFile(p, size);

                long t1 = unbuffered(p);
                long t2 = buffered(p);

                System.out.println(size + " MB\t"
                        + t1 + "\t\t" + t2);

                Files.deleteIfExists(p);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
