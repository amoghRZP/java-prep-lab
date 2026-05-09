package concurrency.OddEven;

public class OddEvenPrint {

    private int number = 1;
    private final int max;

    public OddEvenPrint(int max) {
        this.max = max;
    }

    public synchronized void printOdd() {
        while (number <= max) {
            while (number % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (number <= max) {
                System.out.println(Thread.currentThread().getName() + " -> " + number);
                number++;
                notifyAll();
            }
        }
    }

    public synchronized void printEven() {
        while (number <= max) {
            while (number % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (number <= max) {
                System.out.println(Thread.currentThread().getName() + " -> " + number);
                number++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrint printer = new OddEvenPrint(100);

        Thread oddThread = new Thread(printer::printOdd, "Odd");
        Thread evenThread = new Thread(printer::printEven, "Even");

        oddThread.start();
        evenThread.start();

    }
}