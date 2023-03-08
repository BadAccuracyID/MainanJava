package com.github.badaccuracyid.objects.consumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CallsCounter {

    private final Map<BarConsumer, AtomicLong> callsCount = new ConcurrentHashMap<>();

    public void addCall(BarConsumer consumer) {
        callsCount.putIfAbsent(consumer, new AtomicLong(0));
    }

    public void incrementCall(BarConsumer consumer) {
        callsCount.get(consumer).incrementAndGet();
    }

    public long getCallsCount(BarConsumer consumer) {
        return callsCount.get(consumer).get();
    }

    public void resetCallCount(BarConsumer consumer) {
        callsCount.get(consumer).set(0);
    }

    public void resetAllCallsCount() {
        System.out.println("Resetting all calls count");
        callsCount.replaceAll((barConsumer, atomicLong) -> new AtomicLong(0));
    }

}
