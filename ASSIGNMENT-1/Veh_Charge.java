import java.util.*;
abstract class Vehicle {
    abstract int fee();
}
interface Chargeable {
    int charge(int units);
}
class ElectricCar extends Vehicle implements Chargeable {
    int fee() { return 30; }
    public int charge(int u) { return u * 12; }
}
class Veh_Charge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int u = sc.nextInt();
        ElectricCar e = new ElectricCar();
        System.out.println(e.fee());
        System.out.println(e.charge(u));
    }
}