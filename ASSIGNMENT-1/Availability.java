import java.util.Scanner;
class Availability {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int floor = sc.nextInt();
        int slot = sc.nextInt();
        boolean available = sc.nextBoolean();

        System.out.println(floor);
        System.out.println(slot);
        System.out.println(available);
    }
}