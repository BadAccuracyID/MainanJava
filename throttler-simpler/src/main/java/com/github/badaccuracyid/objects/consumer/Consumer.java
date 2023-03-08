package com.github.badaccuracyid.objects.consumer;

import java.util.Objects;

public record Consumer(String consumerId, int maxRequests) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return consumerId.equals(consumer.consumerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerId);
    }

}
