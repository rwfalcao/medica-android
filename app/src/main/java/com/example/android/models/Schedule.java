package com.example.android.models;

import java.util.List;

public class Schedule {
    Medication med;
    List<String> horarios;

    public Schedule(Medication med, List<String> horarios) {
        this.med = med;
        this.horarios = horarios;
    }

    public Medication getMed() {
        return med;
    }

    public List<String> getHorarios() {
        return horarios;
    }
}
