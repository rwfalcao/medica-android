package com.example.android.util;

import com.example.android.models.User;

import java.util.List;


public class InsertionSort{

    static public void sort(List<User> arr){
        int n = arr.size();
        for (int i=1; i<n; ++i){
            User key = arr.get(i);
            int j = i-1;
            while (j>=0 && arr.get(j).getNome().toString().compareTo(key.getNome().toString()) > 0){

                arr.set(j+1, arr.get(j));
                j = j-1;
            }
            arr.set(j+1, key);

        }
    }

}
