package com.github.badaccuracyid;

import com.github.badaccuracyid.objects.consumer.Consumer;
import com.github.badaccuracyid.objects.provider.Provider;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {

    public Main() {
        var human = new Consumer("human", 2);
        var dwarf = new Consumer("dwarf", 4);

        var provider = new Provider(1000);

        var executor = Executors.newFixedThreadPool(2);
        executor.execute(() ->
                IntStream.range(0, 10).forEach(i -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (provider.isThrottled(human)) {
                        return;
                    }

                    provider.provide(human);
                }));
        executor.execute(() ->
                IntStream.range(0, 10).forEach(i -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (provider.isThrottled(dwarf)) {
                        return;
                    }

                    provider.provide(dwarf);
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
