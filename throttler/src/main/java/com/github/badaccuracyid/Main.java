package com.github.badaccuracyid;

import com.github.badaccuracyid.objects.consumer.BarConsumer;
import com.github.badaccuracyid.objects.consumer.CallsCounter;
import com.github.badaccuracyid.objects.provider.Bartender;
import com.github.badaccuracyid.throttler.ThrottlerTask;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {

    public Main() {
        var callsCounter = new CallsCounter();
        var human = new BarConsumer("Human", 2, callsCounter);
        var dwarf = new BarConsumer("Dwarf", 4, callsCounter);

        var executor = Executors.newFixedThreadPool(2);

        var throttler = new ThrottlerTask(1000, callsCounter);
        var bartender = new Bartender(throttler, callsCounter);

        var count = 10;

        executor.execute(() ->
                IntStream.range(0, count).forEach(it -> {
                    bartender.orderDrink(human);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));

        executor.execute(() ->
                IntStream.range(0, count).forEach(it -> {
                    bartender.orderDrink(dwarf);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Sleeping...");
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor = Executors.newFixedThreadPool(2);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Waking...");
        System.out.println();
        System.out.println();
        System.out.println();

        executor.execute(() ->
                IntStream.range(0, count).forEach(it -> {
                    bartender.orderDrink(human);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));

        executor.execute(() ->
                IntStream.range(0, count).forEach(it -> {
                    bartender.orderDrink(dwarf);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
