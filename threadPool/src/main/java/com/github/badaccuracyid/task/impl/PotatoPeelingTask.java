package com.github.badaccuracyid.task.impl;

import com.github.badaccuracyid.task.Task;

public class PotatoPeelingTask extends Task {

    private static final int POTATO_PEELING_TIME = 200;

    public PotatoPeelingTask(int numPotatoes) {
        super(numPotatoes * POTATO_PEELING_TIME);
    }

    @Override
    public String toString() {
        return "PotatoPeelingTask{" +
                "id=" + getId() +
                ", taskTime=" + getTaskTime() +
                '}';
    }
}
