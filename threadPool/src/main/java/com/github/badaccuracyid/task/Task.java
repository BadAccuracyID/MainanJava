package com.github.badaccuracyid.task;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public abstract class Task {

    private static final AtomicInteger TASK_ID = new AtomicInteger(0);

    private final int id;
    private final int taskTime;

    public Task(int taskTime) {
        this.id = TASK_ID.getAndIncrement();
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskTime=" + taskTime +
                '}';
    }
}
