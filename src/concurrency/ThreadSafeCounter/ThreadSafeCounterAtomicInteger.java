package concurrency.ThreadSafeCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounterAtomicInteger {
    private final AtomicInteger counter = new AtomicInteger(0);
    public void increment() {
        counter.incrementAndGet();
    }
    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        ThreadSafeCounterAtomicInteger counter = new ThreadSafeCounterAtomicInteger();
        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);
        Thread t4 = new Thread(() -> System.out.println(counter.getCounter()));
        Thread t3 = new Thread(counter::increment);
        Thread t5 = new Thread(() -> System.out.println(counter.getCounter()));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
