package com.github.badaccuracyid.impl;

import com.github.badaccuracyid.objects.ASExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manager {

    private final List<ASExecutor> executors = new ArrayList<>();

    public Manager() {
        this.checkAll();
    }

    public void addExecutor(ASExecutor executor) {
        executors.add(executor);
    }

    public void removeExecutor(ASExecutor executor) {
        executors.remove(executor);
    }

    private void checkAll() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> executors.forEach(ASExecutor::check), 0, 1, TimeUnit.SECONDS);
    }


}
