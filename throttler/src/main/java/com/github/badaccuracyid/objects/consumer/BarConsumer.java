package com.github.badaccuracyid.objects.consumer;

import lombok.Getter;

@Getter
public class BarConsumer {

    private final String name;
    private final int allowedCallsPerSecond;

    public BarConsumer(String name, int allowedCallsPerSecond, CallsCounter callsCounter) {
        this.name = name;
        this.allowedCallsPerSecond = allowedCallsPerSecond;

        callsCounter.addCall(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BarConsumer && ((BarConsumer) obj).getName().equals(name);
    }
}
