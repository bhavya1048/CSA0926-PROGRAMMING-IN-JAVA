
public class DamageTest {

    public static void main(String[] args) {

        String records[] = {
            "P1001,192311001,Arun,12,4500",
            "P1002,DAMAGED LINE",
            "P1003,192311003,Divya,7,ABC",
            "",
            "P1004,192311004,Ravi,8,3800"
        };

        for (String line : records) {

            String fields[] = line.split(",", -1);

            if (fields.length != 5) {
                System.out.println("Invalid Record: " + line);
                continue;
            }

            try {
                Integer.parseInt(fields[3]);
                Double.parseDouble(fields[4]);

                System.out.println("Valid Record: " + fields[0]);

            } catch (NumberFormatException e) {
                System.out.println("Invalid Data: " + line);
            }
        }
    }
}
