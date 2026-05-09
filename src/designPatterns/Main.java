package designPatterns;

import designPatterns.singleton.EagerInitializedSingleton;

public class Main {
    public static void main(String[] args) {
        EagerInitializedSingleton singleton = EagerInitializedSingleton.getSingleton();
    }
}
