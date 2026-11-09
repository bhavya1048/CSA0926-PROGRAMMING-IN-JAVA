
public class TicketBenchmark {

    static String usingString(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + "PNR123 PASSENGER COACH FARE ";
        }
        return s;
    }

    static String usingBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("PNR123 PASSENGER COACH FARE ");
        }
        return sb.toString();
    }

    static String usingBuffer(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("PNR123 PASSENGER COACH FARE ");
        }
        return sb.toString();
    }

    static void test(String name, int type, int n) {
        long start = System.nanoTime();

        String result = "";
        if (type == 1)
            result = usingString(n);
        else if (type == 2)
            result = usingBuilder(n);
        else
            result = usingBuffer(n);

        long end = System.nanoTime();

        System.out.println(name + " Time: " +
                (end - start) / 1000000.0 + " ms");
        System.out.println("Characters: " + result.length());
    }

    public static void main(String[] args) {
        int n = 10000;

        test("String", 1, n);
        test("StringBuilder", 2, n);
        test("StringBuffer", 3, n);
    }
}
