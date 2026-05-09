package concurrency.ThreadSafeCounter;

public class ThreadSafeCounterSynchronized {
    int counter = 0;
    public synchronized void increment() {
        counter++;
    }
    public synchronized int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        ThreadSafeCounterSynchronized counter = new ThreadSafeCounterSynchronized();
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
