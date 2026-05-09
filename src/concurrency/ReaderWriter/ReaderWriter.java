package concurrency.ReaderWriter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriter {
    private int shared = 0;

    /*
    With reader-heavy traffic, a writer may wait a long time depending on policy and timing.
    ReentrantReadWriteLock has a fairness option, pass as true
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println("Read value: " + shared);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            shared++;
            System.out.println("Wrote value: " + shared);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static  void main(String[] args) throws InterruptedException {
        ReaderWriter readerWriter = new ReaderWriter();
        Thread t1 = new Thread(() -> {
            while (true) {
                readerWriter.read();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                readerWriter.read();
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                readerWriter.read();
            }
        });

        Thread t4 = new Thread(() -> {
            while (true) {
                readerWriter.read();
            }
        });

        Thread t5 = new Thread(() -> {
            while (true) {
                readerWriter.write();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
