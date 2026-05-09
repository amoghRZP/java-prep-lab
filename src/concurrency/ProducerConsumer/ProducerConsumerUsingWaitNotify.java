package concurrency.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerUsingWaitNotify {

    /*
    This also essentially builds a bounded blocking queue using linked list, max capacity
    and wait, notify, we can choose without bound too.
     */

    private final Queue<Integer> queue =  new LinkedList<>();
    private final int MAX_QUEUE_SIZE = 100;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() >= MAX_QUEUE_SIZE) {
            wait();
        }
        queue.offer(value);
        System.out.println("Produced :" + value);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        System.out.println("Consumed :" + queue.poll());
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerUsingWaitNotify producerConsumer = new ProducerConsumerUsingWaitNotify();
        Thread t1 = new Thread(() -> {
            int i=0;
            while (true) {
                try {
                    producerConsumer.produce(i);
                    i++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }});

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    producerConsumer.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }});

        t1.start();
        t2.start();
    }
}
