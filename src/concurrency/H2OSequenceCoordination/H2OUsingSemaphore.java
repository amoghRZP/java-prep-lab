package concurrency.H2OSequenceCoordination;

import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;

public class H2OUsingSemaphore {
    private final Semaphore hydrogen = new Semaphore(2);
    private final Semaphore oxygen = new Semaphore(1);
    private final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        hydrogen.release(2);
        oxygen.release(1);
    });

    public void hydrogen(Runnable releaseHydrogen) throws Exception {
        hydrogen.acquire();
        releaseHydrogen.run();   // prints "H"
        barrier.await();
    }

    public void oxygen(Runnable releaseOxygen) throws Exception {
        oxygen.acquire();
        releaseOxygen.run();     // prints "O"
        barrier.await();
    }

    public static void main(String[] args) {
        H2OUsingSemaphore h2OUsingSemaphore = new H2OUsingSemaphore();

        Runnable h = () -> System.out.print("H");
        Runnable o = () -> System.out.print("O");

        Thread t1 = new Thread(() -> runHydrogen(h2OUsingSemaphore, h));
        Thread t2 = new Thread(() -> runHydrogen(h2OUsingSemaphore, h));
        Thread t3 = new Thread(() -> runOxygen(h2OUsingSemaphore, o));
        Thread t4 = new Thread(() -> runHydrogen(h2OUsingSemaphore, h));
        Thread t5 = new Thread(() -> runHydrogen(h2OUsingSemaphore, h));
        Thread t6 = new Thread(() -> runOxygen(h2OUsingSemaphore, o));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    private static void runHydrogen(H2OUsingSemaphore h2OUsingSemaphore, Runnable h) {
        try {
            h2OUsingSemaphore.hydrogen(h);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runOxygen(H2OUsingSemaphore h2OUsingSemaphore, Runnable o) {
        try {
            h2OUsingSemaphore.oxygen(o);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
