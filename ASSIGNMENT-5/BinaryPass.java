
import java.io.*;

public class BinaryPass {
    public static void main(String[] args) throws IOException {

        DataOutputStream dos = new DataOutputStream(
            new FileOutputStream("passes.dat"));

        dos.writeUTF("P1001");
        dos.writeUTF("192311001");
        dos.writeUTF("Arun");
        dos.writeInt(12);
        dos.writeDouble(4500.0);

        dos.close();

        System.out.println("Binary Record Saved");
    }
}
