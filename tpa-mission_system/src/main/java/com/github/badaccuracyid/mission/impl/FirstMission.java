package com.github.badaccuracyid.mission.impl;

import com.github.badaccuracyid.mission.Mission;
import com.github.badaccuracyid.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class FirstMission extends Mission {

    @Override
    public List<String> getObjective() {
        return List.of("Get 10 XP");
    }

    @Override
    public Predicate<Player> getPredicate() {
        return player -> player.xp() >= 10;
    }

    @Override
    public List<Callable<Void>> getOnComplete() {
        List<Callable<Void>> list = new ArrayList<>();

        list.add(() -> {
            System.out.println("Mission Complete!");
            return null;
        });

        list.add(() -> {
            System.out.println("You got 10 XP!");
            return null;
        });

        return list;
    }

}
