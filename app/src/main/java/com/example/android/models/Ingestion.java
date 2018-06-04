package com.example.android.models;

public class Ingestion {
    String score;
    String time;
    String date;
    String obs;
    String conf;

    public Ingestion(String score, String time, String date, String obs, String conf) {
        this.score = score;
        this.time = time;
        this.date = date;
        this.conf = conf;

        if (obs.isEmpty()){
            obs = "Sem observação";
        }

        this.obs = obs;
    }
}
