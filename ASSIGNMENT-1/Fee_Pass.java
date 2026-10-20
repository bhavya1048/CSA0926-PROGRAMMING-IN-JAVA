import java.util.*;
class Fee_Pass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minutes = sc.nextInt(), hour = sc.nextInt();
        int bike = (minutes + 59) / 60 * 10;
        int van = (hour >= 9 && hour < 17) ? 60 : 30;
        System.out.println("E-Bike Fee: " + bike);
        System.out.println("Van Fee: " + van);
        System.out.println("One-Day Pass: Valid");
    }
}