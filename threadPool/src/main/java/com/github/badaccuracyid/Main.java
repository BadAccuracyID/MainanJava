package com.github.badaccuracyid;

import com.github.badaccuracyid.task.impl.CoffeeMakingTask;
import com.github.badaccuracyid.task.impl.PotatoPeelingTask;
import com.github.badaccuracyid.worker.Worker;

import java.util.List;
import java.util.concurrent.Executors;

public class Main {

    public Main() {
        var tasks = List.of(
                new PotatoPeelingTask(3),
                new PotatoPeelingTask(6),
                new CoffeeMakingTask(2),
                new CoffeeMakingTask(6),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(2),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(9),
                new PotatoPeelingTask(3),
                new CoffeeMakingTask(2),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(2),
                new CoffeeMakingTask(7),
                new PotatoPeelingTask(4),
                new PotatoPeelingTask(5)
        );

//        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var executor = Executors.newFixedThreadPool(2);

        tasks.stream().map(Worker::new).forEach(executor::execute);
        executor.shutdown();

        while (!executor.isTerminated()) {
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
