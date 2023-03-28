package com.github.badaccuracyid.mission.impl;

import com.github.badaccuracyid.mission.Mission;
import com.github.badaccuracyid.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class ThirdMission extends Mission {

    @Override
    public List<String> getObjective() {
        return List.of("Get 25 XP", "Get to level 2");
    }

    @Override
    public List<Predicate<Player>> getPredicates() {
        List<Predicate<Player>> list = new ArrayList<>();

        list.add(player -> player.xp() >= 25);
        list.add(player -> player.level() >= 2);

        return list;
    }

    @Override
    public List<Callable<Void>> getOnComplete() {
        List<Callable<Void>> list = new ArrayList<>();

        list.add(() -> {
            System.out.println("Mission Complete!");
            return null;
        });

        list.add(() -> {
            System.out.println("You got 25 XP and level 2!");
            return null;
        });

        return list;
    }

}
