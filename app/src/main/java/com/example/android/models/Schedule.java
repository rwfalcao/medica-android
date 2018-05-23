package com.example.android.models;

import java.util.List;

public class Schedule {
    Medication med;
    List<BasicTime> horarios;
    User user;
    String freq;
    String schedId;

    public Schedule(Medication med, List<BasicTime> horarios, User user, String freq) {
        this.med = med;
        this.horarios = horarios;
        this.user = user;
        this.freq = freq;
    }

    public Medication getMed() {
        return med;
    }

    public List<BasicTime> getHorarios() {
        return horarios;
    }

    public User getUser() {
        return user;
    }

    public String getFreq() {
        return freq;
    }

    public String getSchedId() {
        return schedId;
    }

    public void setSchedId(String schedId) {
        this.schedId = schedId;
    }

}
