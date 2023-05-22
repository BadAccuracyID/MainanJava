package com.github.badaccuracyid;

public class Main {
    private int count;

    public Main() {
        this.runExample();
    }

    public static void main(String[] args) {
        // Playing with synchronized methods
        new Main();
    }


    public void runExample() {
        // Create and start two threads
        Thread thread1 = new Thread(new IncrementRunnable());
        Thread thread2 = new Thread(new IncrementRunnable());

        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + count);
    }

    // Non-synchronized method
    public void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    // Synchronized method
    public synchronized void synchronizedIncrement() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    class IncrementRunnable implements Runnable {
        @Override
        public void run() {
            // Call either increment() or synchronizedIncrement() here
//            increment();
            synchronizedIncrement();
        }
    }
}
