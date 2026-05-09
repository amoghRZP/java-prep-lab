package concurrency.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
    private final BlockingQueue<Integer> queue =  new ArrayBlockingQueue<>(100);
    public void produce(int value) throws InterruptedException {
        queue.put(value);
        System.out.println("Produced :" + value);
    }

    public void consume() throws InterruptedException {
        System.out.println("Consumed :" + queue.take());
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerUsingBlockingQueue producerConsumer = new ProducerConsumerUsingBlockingQueue();
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
