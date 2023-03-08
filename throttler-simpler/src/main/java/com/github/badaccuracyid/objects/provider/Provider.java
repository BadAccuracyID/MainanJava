package com.github.badaccuracyid.objects.provider;

import com.github.badaccuracyid.objects.consumer.Consumer;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Provider {

    private final Map<Consumer, AtomicLong> consumerMap = Collections.synchronizedMap(new WeakHashMap<>());

    public Provider(long throttlePeriod) {
        var executor = Executors.newSingleThreadScheduledExecutor(
                runnable -> {
                    var thread = new Thread(runnable);
                    thread.setDaemon(true);
                    return thread;
                }
        );

        executor.scheduleAtFixedRate(this::reset, throttlePeriod, throttlePeriod, TimeUnit.MILLISECONDS);
    }

    public void provide(Consumer consumer) {
        AtomicLong atomicLong = consumerMap.get(consumer);
        if (atomicLong == null) {
            atomicLong = new AtomicLong(0);
            consumerMap.put(consumer, atomicLong);
        }

        atomicLong.incrementAndGet();
        System.out.println("Consumer " + consumer.consumerId() + " is at " + atomicLong.get() + " / " + consumer.maxRequests() + " requests");
    }

    public boolean isThrottled(Consumer consumer) {
        AtomicLong atomicLong = consumerMap.get(consumer);
        if (atomicLong == null) {
            return false;
        }

        return atomicLong.get() >= consumer.maxRequests();
    }

    public void reset() {
        System.out.println("Resetting");
        consumerMap.clear();
    }
}
