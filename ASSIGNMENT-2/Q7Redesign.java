
public class Q7Redesign {

    static int usingString(int n) {
        String result = "";

        for (int i = 0; i < n; i++) {
            result = result + "Java";
        }

        return result.length();
    }

    static int usingBuilder(int n) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            result.append("Java");
        }

        return result.length();
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        System.out.println("Size\tString(ns)\tBuilder(ns)");

        for (int n : sizes) {

            long start1 = System.nanoTime();
            int length1 = usingString(n);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            int length2 = usingBuilder(n);
            long end2 = System.nanoTime();

            System.out.println(n + "\t" +
                (end1 - start1) + "\t" +
                (end2 - start2));

            if (length1 != length2) {
                System.out.println("Output mismatch");
            }
        }
    }
}
