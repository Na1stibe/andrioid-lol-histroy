package com.example.lol_battlerecode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Match_List {
    @SerializedName("matches")
    private List<Match> matches = new ArrayList<>();

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatch(List<Match> match) {
        this.matches = matches;
    }



    public static class Match {
        @SerializedName("gameId")
        private String gameId;

        @SerializedName("champion")
        private String champion;

        @SerializedName("queue")
        private String queue;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getChampion() {
            return champion;
        }

        public void setChampion(String champion) {
            this.champion = champion;
        }

        public String getQueue() {
            return queue;
        }

        public void setQueue(String queue) {
            this.queue = queue;
        }
    }

}
