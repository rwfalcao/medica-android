package com.example.android.util;

public class IdControl {
    private static int userId = 0;

    public void userIdIncrement(){
        userId++;
    }

    public int returnId(){
        userIdIncrement();
        return userId;
    }
}


