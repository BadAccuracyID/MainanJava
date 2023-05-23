package com.github.badaccuracyid;

import com.github.badaccuracyid.tasks.PausableTask;

public class Main {

    public Main() {
        PausableTask task = new PausableTask();

        Thread thread = new Thread(task);
        thread.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {
        }

        task.pause();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }

        task.resume();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {
        }

        task.stop();
    }

    public static void main(String[] args) {
        new Main();
    }

}
