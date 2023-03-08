package com.github.badaccuracyid.task.impl;

import com.github.badaccuracyid.task.Task;

public class CoffeeMakingTask extends Task {

    private static final int COFFEE_MAKING_TIME = 100;

    public CoffeeMakingTask(int numCups) {
        super(numCups * COFFEE_MAKING_TIME);
    }

    @Override
    public String toString() {
        return "CoffeeMakingTask{" +
                "id=" + getId() +
                ", taskTime=" + getTaskTime() +
                '}';
    }
}
