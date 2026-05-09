package concurrency.RateLimitPermit;


public class RateLimitUsingSemaphoreLikePermitSystemUsingSyncWaitNotify {
    private int counter =  5;
    public synchronized boolean acquire() throws InterruptedException {
        while (counter == 0) {
            wait();
        }
        counter--;
        return true;
    }

    public synchronized void release() throws InterruptedException {
        counter++;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimitUsingSemaphoreLikePermitSystemUsingSyncWaitNotify rateLimiter = new RateLimitUsingSemaphoreLikePermitSystemUsingSyncWaitNotify();
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
