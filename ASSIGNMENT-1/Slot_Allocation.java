import java.util.Scanner;
class Slot_Allocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] slots = new String[2];

        for (int i = 0; i < 2; i++)
            slots[i] = sc.next();

        System.out.println("Slot 1: " + slots[0]);
        System.out.println("Slot 2: " + slots[1]);
    }
}