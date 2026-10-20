import java.util.*;
class Fee_Calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bike = sc.nextInt();
        int car = sc.nextInt();
        int b = (bike + 59) / 60 * 10;
        int c = (car + 59) / 60 * 30;
        System.out.println("Bike Fee: " + b);
        System.out.println("Car Fee: " + c);
        System.out.println("Total: " + (b + c));
    }
}