package com.github.badaccuracyid.player;

public class Player {

    private final String name;
    private int level;
    private int xp;

    public Player(String name, int level, int xp) {
        this.name = name;
        this.level = level;
        this.xp = xp;
    }

    public String name() {
        return name;
    }

    public int level() {
        return level;
    }

    public int xp() {
        return xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
