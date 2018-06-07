package com.example.android.models;

public class BasicDate {
    private int day;
    private int month;
    private int year;

    public BasicDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String formatedDate(){
        return Integer.toString(this.day)+"/"+Integer.toString(this.month)+"/"+Integer.toString(this.year);
    }
}
