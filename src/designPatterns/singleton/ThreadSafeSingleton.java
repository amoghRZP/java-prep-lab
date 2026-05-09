package designPatterns.singleton;

/**
 * Use synchronized block inside the if loop and volatile variable
 * Pros:
 * - Thread safety is guaranteed
 * - Client application can pass arguments
 * - Lazy initialization achieved
 * - Synchronization overhead is minimal and applicable only for first few threads when the variable is null.
 * Cons:
 * - Extra if condition
 * Ref: <a href="https://www.digitalocean.com/community/tutorials/thread-safety-in-java-singleton-classes">...</a>
 */
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    private static final Object mutex = new Object();

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        ThreadSafeSingleton result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new ThreadSafeSingleton();
                }
            }
        }
        return result;
    }

}
