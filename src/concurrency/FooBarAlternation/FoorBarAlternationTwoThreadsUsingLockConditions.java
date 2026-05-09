package concurrency.FooBarAlternation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FoorBarAlternationTwoThreadsUsingLockConditions {
    private final String foo = "foo";
    private final String bar = "bar";
    private boolean fooTurn = true;
    ReentrantLock lock = new ReentrantLock();
    Condition printFoo = lock.newCondition();
    Condition printBar = lock.newCondition();

    public void printFoo() throws InterruptedException {
        lock.lock();
        try {
            while (!fooTurn) {
                printFoo.await();
            }
            System.out.println(foo);
            fooTurn = false;
            printBar.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printBar() throws InterruptedException {
        lock.lock();
        try {
            while (fooTurn) {
                printBar.await();
            }
            System.out.println(bar);
            fooTurn = true;
            printFoo.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FoorBarAlternationTwoThreadsUsingLockConditions foorBarAlternation = new FoorBarAlternationTwoThreadsUsingLockConditions();
        Thread thread1 = new Thread(() -> {
            while(true) {
                try {
                    foorBarAlternation.printFoo();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true) {
                try {
                    foorBarAlternation.printBar();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
