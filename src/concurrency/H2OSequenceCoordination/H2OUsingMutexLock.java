package concurrency.H2OSequenceCoordination;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class H2OUsingMutexLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition hydrogenCondition = lock.newCondition();
    private final Condition oxygenCondition = lock.newCondition();
    private final Condition barrierCondition = lock.newCondition();

    private int hydrogenCount = 0;
    private int oxygenCount = 0;
    private int arrived = 0;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hydrogenCount == 2) {
                hydrogenCondition.await();
            }

            hydrogenCount++;
            releaseHydrogen.run();
            arrived++;

            if (hydrogenCount == 2 && oxygenCount == 1) {
                barrierCondition.signalAll();
            } else {
                while (!(hydrogenCount == 2 && oxygenCount == 1)) {
                    barrierCondition.await();
                }
            }

            arrived--;
            if (arrived == 0) {
                hydrogenCount = 0;
                oxygenCount = 0;
                hydrogenCondition.signalAll();
                oxygenCondition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (oxygenCount == 1) {
                oxygenCondition.await();
            }

            oxygenCount++;
            releaseOxygen.run();
            arrived++;

            if (hydrogenCount == 2 && oxygenCount == 1) {
                barrierCondition.signalAll();
            } else {
                while (!(hydrogenCount == 2 && oxygenCount == 1)) {
                    barrierCondition.await();
                }
            }

            arrived--;
            if (arrived == 0) {
                hydrogenCount = 0;
                oxygenCount = 0;
                hydrogenCondition.signalAll();
                oxygenCondition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        H2OUsingMutexLock h2OUsingMutexLock = new H2OUsingMutexLock();

        Runnable h = () -> System.out.print("H");
        Runnable o = () -> System.out.print("O");

        Thread t1 = new Thread(() -> runHydrogen(h2OUsingMutexLock, h));
        Thread t2 = new Thread(() -> runHydrogen(h2OUsingMutexLock, h));
        Thread t3 = new Thread(() -> runOxygen(h2OUsingMutexLock, o));
        Thread t4 = new Thread(() -> runHydrogen(h2OUsingMutexLock, h));
        Thread t5 = new Thread(() -> runHydrogen(h2OUsingMutexLock, h));
        Thread t6 = new Thread(() -> runOxygen(h2OUsingMutexLock, o));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    private static void runHydrogen(H2OUsingMutexLock h2OUsingMutexLock, Runnable h) {
        try {
            h2OUsingMutexLock.hydrogen(h);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runOxygen(H2OUsingMutexLock h2OUsingMutexLock, Runnable o) {
        try {
            h2OUsingMutexLock.oxygen(o);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
