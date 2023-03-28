package com.github.badaccuracyid;

import com.github.badaccuracyid.mission.MissionManager;
import com.github.badaccuracyid.mission.impl.FirstMission;
import com.github.badaccuracyid.mission.impl.SecondMission;
import com.github.badaccuracyid.mission.impl.ThirdMission;
import com.github.badaccuracyid.player.Player;
import lombok.Getter;

import java.util.Scanner;

public class Main {

    @Getter
    private final Player player = new Player("BadAccuracyID", 1, 0);
    private final Scanner scanner = new Scanner(System.in);
    private final MissionManager missionManager = new MissionManager(this);

    private Main() {
        missionManager.addMission(new FirstMission());
        missionManager.addMission(new SecondMission());
        missionManager.addMission(new ThirdMission());

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                player.setXp(player.xp() + 5);
                System.out.println("You got 5 XP!");
                System.out.println("Current XP: " + player.xp());
            } else if (input.equalsIgnoreCase("l")) {
                player.setLevel(player.level() + 1);
                System.out.println("You leveled up!");
                System.out.println("Current Level: " + player.level());
            } else if (input.equalsIgnoreCase("m")) {
                missionManager.getMissionQueue().forEach(mission -> {
                    System.out.println("Mission: " + mission.getClass().getSimpleName());
                    System.out.println("Objective: " + mission.getObjective());
                    System.out.println("Completed: " + mission.getCompletion());
                    System.out.println(" ");
                });
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
