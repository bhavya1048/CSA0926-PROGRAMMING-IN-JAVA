import java.util.*;
class Vehicle {
    int rate;
    Vehicle(int r) { rate = r; }
    int fee(int m) { return (m + 59) / 60 * rate; }
}
class Fee_Calculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int m = sc.nextInt();
        Vehicle v = new Vehicle(type.equals("Car") ? 30 : 10);
        System.out.println(v.fee(m));
    }
}