
class ScannerTask implements Runnable {

    private String station;
    private int scripts;

    ScannerTask(String station, int scripts) {
        this.station = station;
        this.scripts = scripts;
    }

    public void run() {

        int count = 0;

        try {
            for (int i = 1; i <= scripts; i++) {
                Thread.sleep(100);

                count++;
            }

            System.out.println(station +
                " scanned " + count + " scripts");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(station + " interrupted");
        }
    }
}

public class ScannerDemo {

    public static void main(String[] args)
            throws InterruptedException {

        Thread[] stations = new Thread[4];

        stations[0] = new Thread(
            new ScannerTask("Station 1", 5));

        stations[1] = new Thread(
            new ScannerTask("Station 2", 4));

        stations[2] = new Thread(
            new ScannerTask("Station 3", 6));

        stations[3] = new Thread(
            new ScannerTask("Station 4", 3));

        for (Thread t : stations) {
            t.start();
        }

        for (Thread t : stations) {
            System.out.println(
                "Alive: " + t.isAlive());
        }

        for (Thread t : stations) {
            t.join();
        }

        System.out.println("All stations completed");
    }
}
