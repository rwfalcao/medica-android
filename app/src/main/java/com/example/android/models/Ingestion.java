package com.example.android.models;

public class Ingestion {
    String score;
    String time;
    String date;
    String obs;

    public Ingestion(String score, String time, String date, String obs) {
        this.score = score;
        this.time = time;
        this.date = date;

        if (obs.isEmpty()){
            obs = "Sem observação";
        }

        this.obs = obs;
    }
}
