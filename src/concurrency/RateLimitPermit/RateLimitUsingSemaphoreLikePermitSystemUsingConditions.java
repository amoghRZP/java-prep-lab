package concurrency.RateLimitPermit;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimitUsingSemaphoreLikePermitSystemUsingConditions {
    private int counter =  5;
    ReentrantLock lock = new ReentrantLock();
    Condition permitsAvailable = lock.newCondition();
    public boolean acquire() throws InterruptedException {
        lock.lock();
        try {
            while (counter == 0) {
                permitsAvailable.await();
            }
            counter--;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void release() throws InterruptedException {
        lock.lock();
        try {
            counter++;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimitUsingSemaphoreLikePermitSystemUsingConditions rateLimiter = new RateLimitUsingSemaphoreLikePermitSystemUsingConditions();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 1 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 2 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 3 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t4 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 4 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t5 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 5 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        Thread t6 = new Thread(() -> {
            while (true) {
                try {
                    if (rateLimiter.acquire()) {
                        System.out.println("Thread 6 got the permit");
                        Thread.sleep(1000);
                        rateLimiter.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
