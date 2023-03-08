package com.github.badaccuracyid.objects.provider;

import com.github.badaccuracyid.objects.consumer.BarConsumer;
import com.github.badaccuracyid.objects.consumer.CallsCounter;
import com.github.badaccuracyid.throttler.ThrottlerTask;

public class Bartender {

    private final CallsCounter callsCounter;

    public Bartender(ThrottlerTask throttler, CallsCounter callsCounter) {
        this.callsCounter = callsCounter;

        throttler.start();
    }

    public void orderDrink(BarConsumer consumer) {
        var buyerName = consumer.getName();
        var callsCount = callsCounter.getCallsCount(consumer);
        if (callsCount >= consumer.getAllowedCallsPerSecond()) {
            System.out.println("I'm sorry " + buyerName + ", but you have reached your limit of " + consumer.getAllowedCallsPerSecond() + " drinks per second.");
            return;
        }

        callsCounter.incrementCall(consumer);
        System.out.println("Serving " + buyerName + " a drink. This is their " + ++callsCount + " drink.");
    }
}
