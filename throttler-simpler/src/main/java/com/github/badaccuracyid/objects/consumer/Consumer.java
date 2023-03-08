package com.github.badaccuracyid.objects.consumer;

public record Consumer(String consumerId, int maxRequests) {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Consumer && ((Consumer) obj).consumerId().equals(this.consumerId);
    }
}
