package concurrency;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello I am thread2 & my name is " + Thread.currentThread().getName());
            }
        });
        Thread newThread = new NewThread();

        System.out.println("Hello I am probably main thread thread & my name is " + Thread.currentThread().getName());
        thread2.start();
        newThread.start();
        //Thread.sleep(10000);
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello I am new thread & my name is " + Thread.currentThread().getName());
            System.out.println("Hello I am new thread & my name is " + this.getName());
        }
    }
}
