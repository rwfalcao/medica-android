package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.android.adapters.QuantidadeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfirmaScheduleActivity extends AppCompatActivity {

    ExpandableListView qtdListView;
    List<String> listHeader;
    HashMap<String, List<String>> hashMap;
    QuantidadeAdapter qtdAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_schedule);

        qtdListView = (ExpandableListView) findViewById(R.id.qtdDiariaListView);
        listHeader = new ArrayList<>();
        hashMap = new HashMap<>();

        qtdAdapter = new QuantidadeAdapter(this, listHeader, hashMap);
        qtdListView.setAdapter(qtdAdapter);

        iniciarConteudo();
    }

    public void iniciarConteudo(){
        List<String> lista1 = new ArrayList<>();
        lista1.add("Item 1");
        lista1.add("Item 2");
        lista1.add("Item 3");

        List<String> lista2 = new ArrayList<>();
        lista2.add("Item 1");
        lista2.add("Item 2");
        lista2.add("Item 3");

        List<String> lista3 = new ArrayList<>();
        lista3.add("Item 1");
        lista3.add("Item 2");
        lista3.add("Item 3");

        listHeader.add("lista1");
        listHeader.add("lista2");
        listHeader.add("lista3");

        hashMap.put(listHeader.get(0), lista1);
        hashMap.put(listHeader.get(1), lista2);
        hashMap.put(listHeader.get(2), lista3);

        qtdAdapter.notifyDataSetChanged();
    }
}
