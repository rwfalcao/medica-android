package com.example.android.util;

import com.example.android.models.Medication;

import java.util.List;

public class QuickSort {
    static public int partition(List<Medication> arr, int low, int high){
        Medication pivot = arr.get(high);
        int i = (low-1);
        for (int j=low; j<high; j++){

            if ( arr.get(j).getNome().toLowerCase().compareTo( pivot.getNome().toLowerCase() ) <= 0 ){
                i++;
                Medication temp = arr.get(i);
                arr.set(i, arr.get(j) );
                arr.set(j, temp);
            }
        }

        Medication temp2 = arr.get(i+1);
        arr.set( (i+1), arr.get(high));
        arr.set(high, temp2);

        return i+1;
    }

    static public void sort(List<Medication> arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            sort(arr, low, (pi-1) );
            sort(arr, (pi+1), high);
        }
    }

}