
import java.io.*;

public class BufferedCounter {
    public static void main(String[] args) {

        String input = "Arun\nDivya\nRavi\nKumar\nPriya";

        BufferedReader br = new BufferedReader(
            new StringReader(input)
        );

        int count = 0;
        String name;

        System.out.println("Attendance Records:");

        try {
            while ((name = br.readLine()) != null) {
                System.out.println(name);
                count++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }

        System.out.println("Total Attendance: " + count);
    }
}
