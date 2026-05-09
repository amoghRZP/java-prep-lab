package concurrency.PrintOneTwoThreeThreeThreads;

/*
With ReentrantLock + Condition:

you can wake only the specific next thread

With synchronized + wait/notifyAll:

you wake everyone
only the correct one proceeds
others go back to waiting
 */
public class PrintOneTwoThreeUsingWaitNotify {
    private final Object lock = new Object();
    private int turn = 1;

    public void printOne() throws InterruptedException {
        synchronized (lock) {
            while (turn != 1) {
                lock.wait();
            }
            System.out.print("1 ");
            turn = 2;
            lock.notifyAll();
        }
    }

    public void printTwo() throws InterruptedException {
        synchronized (lock) {
            while (turn != 2) {
                lock.wait();
            }
            System.out.print("2 ");
            turn = 3;
            lock.notifyAll();
        }
    }

    public void printThree() throws InterruptedException {
        synchronized (lock) {
            while (turn != 3) {
                lock.wait();
            }
            System.out.print("3 ");
            turn = 1;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        PrintOneTwoThreeUsingWaitNotify printer = new PrintOneTwoThreeUsingWaitNotify();

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    printer.printOne();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    printer.printTwo();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    printer.printThree();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}