package com.github.badaccuracyid.mission;

import com.github.badaccuracyid.Main;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MissionManager {

    private final Main main;
    @Getter
    private final BlockingQueue<Mission> missionQueue;
    @Getter
    @Setter
    private Mission currentMission;

    public MissionManager(Main main) {
        this.main = main;
        this.missionQueue = new LinkedBlockingQueue<>();
        this.startChecking();
    }

    public void addMission(Mission mission) {
        missionQueue.add(mission);
    }

    public void removeMission(Mission mission) {
        missionQueue.remove(mission);
    }

    private void startChecking() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    if (currentMission == null) {
                        currentMission = missionQueue.poll();
                    }

                    if (currentMission != null) {

                        boolean predicate = currentMission.getPredicate().test(main.getPlayer());
                        System.out.println("Current Mission: " + currentMission.getName() + " | Completed: " + predicate);
                        System.out.println("Current XP: " + main.getPlayer().xp());
                        if (predicate) {
                            currentMission.setCompleted(true);
                        }

                        if (currentMission.isCompleted()) {
                            currentMission.getOnComplete().forEach(callable -> {
                                try {
                                    callable.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            currentMission = null;
                        }
                    }
                }, 0, 1, TimeUnit.SECONDS);
    }
}
