package concurrency.PrintOneTwoThreeThreeThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
With ReentrantLock + Condition:

you can wake only the specific next thread

With synchronized + wait/notifyAll:

you wake everyone
only the correct one proceeds
others go back to waiting
 */

public class PrintOneTwoThreeUsingConditions {
    private final ReentrantLock lock = new ReentrantLock();
    private int lastPrint = 3;
    private final Condition printOneReady = lock.newCondition();
    private final Condition printTwoReady = lock.newCondition();
    private final Condition printThreeReady = lock.newCondition();


    public void printOne() throws InterruptedException {
        lock.lock();
        try {
            while (lastPrint != 3) {
                printOneReady.await();
            }
            System.out.print("1 ");
            lastPrint = 1;
            printTwoReady.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printTwo() throws InterruptedException {
        lock.lock();
        try {
            while (lastPrint != 1) {
                printTwoReady.await();
            }
            System.out.print("2 ");
            lastPrint = 2;
            printThreeReady.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printThree() throws InterruptedException {
        lock.lock();
        try {
            while (lastPrint != 2) {
                printThreeReady.await();
            }
            System.out.print("3 ");
            lastPrint = 3;
            printOneReady.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOneTwoThreeUsingConditions printOneTwoThreeUsingConditions = new PrintOneTwoThreeUsingConditions();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    printOneTwoThreeUsingConditions.printOne();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    printOneTwoThreeUsingConditions.printTwo();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    printOneTwoThreeUsingConditions.printThree();
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
