package com.github.badaccuracyid;

import com.github.badaccuracyid.mission.MissionManager;
import com.github.badaccuracyid.mission.impl.FirstMission;
import com.github.badaccuracyid.mission.impl.SecondMission;
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

        while (true) {
            if (scanner.nextLine().equalsIgnoreCase("xp")) {
                player.setXp(player.xp() + 5);
                System.out.println("You got 5 XP!");
                System.out.println("Current XP: " + player.xp());
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
