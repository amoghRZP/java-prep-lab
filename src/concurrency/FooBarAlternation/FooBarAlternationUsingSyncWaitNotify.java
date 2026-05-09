package org.example.egress.concurrency.FooBarAlternation;

public class FooBarAlternationUsingSyncWaitNotify {
    private final Object lock = new Object();
    private boolean fooTurn = true;

    public void printFoo() throws InterruptedException {
        synchronized (lock) {
            while (!fooTurn) {
                lock.wait();
            }
            System.out.println("foo");
            fooTurn = false;
            lock.notifyAll();
        }
    }

    public void printBar() throws InterruptedException {
        synchronized (lock) {
            while (fooTurn) {
                lock.wait();
            }
            System.out.println("bar");
            fooTurn = true;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        org.example.egress.concurrency.FooBarAlternation.FooBarAlternationUsingSyncWaitNotify fooBar = new org.example.egress.concurrency.FooBarAlternation.FooBarAlternationUsingSyncWaitNotify();

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    fooBar.printFoo();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    fooBar.printBar();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}