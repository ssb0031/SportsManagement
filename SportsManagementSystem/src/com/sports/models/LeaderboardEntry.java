package com.sports.models;

public class LeaderboardEntry {
    private String username;
    private int wins;
    private int losses;
    private int draws;

    public LeaderboardEntry(String username, int wins, int losses, int draws) {
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }
}
