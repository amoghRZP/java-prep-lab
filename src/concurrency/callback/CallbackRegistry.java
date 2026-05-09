package concurrency.callback;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CallbackRegistry {
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private final Lock readLock = rw.readLock();
    private final Lock writeLock = rw.writeLock();

    private volatile boolean eventFired = false;
    private final BlockingQueue<Runnable> pending = new ArrayBlockingQueue<>(100); {
    };

    public void regCb(Runnable cb) {
        // Fast path after event has fired

        readLock.lock();
        try {
            if (eventFired) {
                cb.run();
                return;
            }
            pending.add(cb);
        } finally {
            readLock.unlock();
        }
    }

    public void eventFired() throws InterruptedException {
        writeLock.lock();
        try {
            eventFired = true;
        } finally {
            writeLock.unlock();
        }

        while (pending.remainingCapacity() > 0) {
            pending.take().run();
        }
    }
}