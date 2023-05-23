package com.github.badaccuracyid.tasks;

public class PausableTask implements Runnable {

    private final Object pauseLock = new Object();
    private boolean running = true;
    private boolean paused = false;

    @Override
    public void run() {
        while (true) {
            synchronized (pauseLock) {
                if (!running) {
                    break;
                }

                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                    if (!running) {
                        break;
                    }
                }

                System.out.println("Hello world!");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public void stop() {
        running = false;
        resume();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }
}
