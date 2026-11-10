
import java.io.*;

class Resident implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    String regNo;
    transient String password;
    transient Object connection;

    Resident(String name, String regNo, String password) {
        this.name = name;
        this.regNo = regNo;
        this.password = password;
    }
}

public class Hostel {
    public static void main(String[] args) {

        Resident r = new Resident(
            "Arun", "192311001", "abc123"
        );

        try {
            ByteArrayOutputStream bout =
                new ByteArrayOutputStream();

            ObjectOutputStream out =
                new ObjectOutputStream(bout);

            out.writeObject(r);
            out.close();

            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bout.toByteArray())
            );

            Resident result = (Resident) in.readObject();

            System.out.println("Name: " + result.name);
            System.out.println("Register No: " + result.regNo);
            System.out.println("Password: " + result.password);

            in.close();

        } catch (Exception e) {
            System.out.println("Serialization Error");
        }
    }
}
