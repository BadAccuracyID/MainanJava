package com.github.badaccuracyid.mission;

import com.github.badaccuracyid.player.Player;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public abstract class Mission {

    private final String name = getClass().getSimpleName();
    private double completion = 0;

    public String getName() {
        return name;
    }

    public double getCompletion() {
        return completion;
    }

    public void setCompletion(double completion) {
        this.completion = completion;
    }

    public double calculateCompletion(Player player) {
        double total = getPredicates().size();
        double completed = 0;
        for (Predicate<Player> predicate : getPredicates()) {
            if (predicate.test(player)) {
                completed++;
            }
        }
        setCompletion(completed / total);
        return getCompletion();
    }


    public abstract List<String> getObjective();

    public abstract List<Predicate<Player>> getPredicates();

    public abstract List<Callable<Void>> getOnComplete();

}
