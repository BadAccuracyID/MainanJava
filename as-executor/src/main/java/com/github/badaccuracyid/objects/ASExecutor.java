package com.github.badaccuracyid.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ASExecutor {

    private final Supplier<ScheduledExecutorService> executorSupplier;
    private final Callable<Void> task;
    private final long delay;
    private final long period;
    private final TimeUnit timeUnit;

    @Nullable
    private Predicate<Void> runningCondition;

    private ScheduledExecutorService executor;

    public void check() {
        if (runningCondition != null && runningCondition.test(null)) {
            if (executor == null) {
                System.out.println("Starting executor");
                executor = executorSupplier.get();
                System.out.println("Executor got");

                System.out.println("Scheduling task");
                executor.scheduleAtFixedRate(() -> {
                    try {
                        task.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, delay, period, timeUnit);
                System.out.println("Task scheduled");
            }
        } else {
            if (executor != null) {
                System.out.println("Shutting down executor");
                executor.shutdown();
                executor = null;
                System.out.println("Executor shut down");
            }
        }
    }


    public static class ASExecutorBuilder {
        private Supplier<ScheduledExecutorService> executor;
        private Callable<Void> task;
        private long delay = 0;
        private long period = 1;
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        @Nullable
        private Predicate<Void> runningCondition;

        public static ASExecutorBuilder builder() {
            return new ASExecutorBuilder();
        }

        public ASExecutorBuilder executor(Supplier<ScheduledExecutorService> executor) {
            this.executor = executor;
            return this;
        }

        public ASExecutorBuilder task(Callable<Void> task) {
            this.task = task;
            return this;
        }

        public ASExecutorBuilder delay(long delay) {
            this.delay = delay;
            return this;
        }

        public ASExecutorBuilder period(long period) {
            this.period = period;
            return this;
        }

        public ASExecutorBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public ASExecutorBuilder runningCondition(Predicate<Void> runningCondition) {
            this.runningCondition = runningCondition;
            return this;
        }

        public ASExecutor build() {
            return new ASExecutor(executor, task, delay, period, timeUnit, runningCondition, null);
        }
    }

}
