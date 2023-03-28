package com.github.badaccuracyid.mission;

import com.github.badaccuracyid.player.Player;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public abstract class Mission {

    private final String name = getClass().getSimpleName();

    public String getName() {
        return name;
    }

    private boolean completed = false;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public abstract List<String> getObjective();

    public abstract Predicate<Player> getPredicate();

    public abstract List<Callable<Void>> getOnComplete();

}
