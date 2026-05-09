package designPatterns.singleton;

/**
 * Create the instance variable at the time of class loading.
 * Pros:
 * - Thread safety without synchronization
 * - Easy to implement
 * Cons:
 * - Early creation of resource that might not be used in the application.
 * - The client application can’t pass any argument, so we can’t reuse it. For example, having a
 *  generic singleton class for database connection where client application supplies database server properties.
 */
public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton singleton = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {}
    public static EagerInitializedSingleton getSingleton() {
        return singleton;
    }
}
