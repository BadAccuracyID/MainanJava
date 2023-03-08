package com.github.badaccuracyid.dao;

import lombok.Data;

@Data
public class Player {

    private int playerId;
    private String playerName;
    private int kills, deaths;

    public double getKDR() {
        return (double) kills / deaths;
    }

}
