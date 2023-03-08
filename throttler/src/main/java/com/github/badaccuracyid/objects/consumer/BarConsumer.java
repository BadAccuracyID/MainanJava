package com.github.badaccuracyid.objects.consumer;

import lombok.Getter;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarConsumer that = (BarConsumer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
