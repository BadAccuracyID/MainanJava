package com.github.badaccuracyid.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class PlayerDao implements Dao<Player> {

    private final Map<Integer, Player> players = new WeakHashMap<>();

    @Override
    public Optional<Player> get(int id) {
        return Optional.ofNullable(players.get(id));
    }

    @Override
    public List<Player> getAll() {
        return players.values().stream().toList();
    }

    @Override
    public void save(Player player) {
        players.put(player.getPlayerId(), player);
    }

    @Override
    public void update(Player player, String[] params) {
        for (String param : params) {
            String[] split = param.split("=");
            switch (split[0]) {
                case "playerName" -> player.setPlayerName(split[1]);
                case "kills" -> player.setKills(Integer.parseInt(split[1]));
                case "deaths" -> player.setDeaths(Integer.parseInt(split[1]));
            }
        }

        players.put(player.getPlayerId(), player);
    }

    @Override
    public void delete(Player player) {
        players.remove(player.getPlayerId());
    }
}
