package com.github.badaccuracyid.throttler;

import com.github.badaccuracyid.objects.consumer.CallsCounter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThrottlerTask {

    private final int throttlePeriod;
    private final CallsCounter callsCounter;

    public ThrottlerTask(int throttlePeriod, CallsCounter callsCounter) {
        this.throttlePeriod = throttlePeriod;
        this.callsCounter = callsCounter;
    }

    public void start() {
        var executor = Executors.newSingleThreadScheduledExecutor(
                runnable -> {
                    var thread = Executors.defaultThreadFactory().newThread(runnable);
                    thread.setDaemon(true);
                    return thread;
                }
        );
        executor.scheduleAtFixedRate(callsCounter::resetAllCallsCount, 0, throttlePeriod, TimeUnit.MILLISECONDS);

//        new Timer(true)
//                .schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        callsCounter.resetAllCallsCount();
//                    }
//                }, 0, throttlePeriod);
    }
}
