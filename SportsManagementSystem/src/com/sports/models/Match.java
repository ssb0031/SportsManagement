package com.sports.models;

public class Match {
    private int matchId;
    private String game;
    private String scheduledDate;
    private String location;

    public Match(int matchId, String game, String scheduledDate, String location) {
        this.matchId = matchId;
        this.game = game;
        this.scheduledDate = scheduledDate;
        this.location = location;
    }

    // Getters and Setters
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
