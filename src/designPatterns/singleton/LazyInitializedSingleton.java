package designPatterns.singleton;

/**
 * Synchronize the getInstance() method.
 * Pros:
 * - Thread safety is guaranteed.
 * - Client application can pass parameters
 * - Lazy initialization achieved
 * Cons:
 * - Slow performance because of locking overhead.
 * - Unnecessary synchronization that is not required once the instance variable is initialized.
 */
public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {}

    public synchronized static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
