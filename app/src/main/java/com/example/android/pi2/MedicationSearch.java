package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.adapters.MedicationAdapter;
import com.example.android.models.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationSearch extends AppCompatActivity {

    Button medSearchBtn;
    TextView jsonContent;
    RecyclerView medRecView;
    MedicationAdapter medAdapter;
    List<Medication> medList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_search);

        medList = new ArrayList<>();

        medRecView = findViewById(R.id.medRecycleView);
        medRecView.setHasFixedSize(true);
        medRecView.setLayoutManager(new LinearLayoutManager(this));

        medList.add(new Medication("VIROTIN", "ACICLOVIR", "ASPEN PHARMA INDÚSTRIA FARMACÊUTICA LTDA", "400 MG COM CT BL AL PLAS INC X 70", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 331.30));
        medList.add(new Medication("CANCIDAS", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26));
        medList.add(new Medication("NOLUPRON DEPOTME", "ACETATO DE LEUPRORRELINA", "ABBVIE FARMACÊUTICA LTDA", "5,0 MG/ML SOL INJ PT PLA…SER + 15 SACHETS ALCOOL", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 725.23));
        medList.add(new Medication("VIROTIN", "ACICLOVIR", "ASPEN PHARMA INDÚSTRIA FARMACÊUTICA LTDA", "400 MG COM CT BL AL PLAS INC X 70", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 331.30));
        medList.add(new Medication("CANCIDAS", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26));
        medList.add(new Medication("NOLUPRON DEPOTME", "ACETATO DE LEUPRORRELINA", "ABBVIE FARMACÊUTICA LTDA", "5,0 MG/ML SOL INJ PT PLA…SER + 15 SACHETS ALCOOL", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 725.23));
        medList.add(new Medication("VIROTIN", "ACICLOVIR", "ASPEN PHARMA INDÚSTRIA FARMACÊUTICA LTDA", "400 MG COM CT BL AL PLAS INC X 70", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 331.30));
        medList.add(new Medication("CANCIDAS", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26));
        medList.add(new Medication("NOLUPRON DEPOTME", "ACETATO DE LEUPRORRELINA", "ABBVIE FARMACÊUTICA LTDA", "5,0 MG/ML SOL INJ PT PLA…SER + 15 SACHETS ALCOOL", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 725.23));
        medList.add(new Medication("VIROTIN", "ACICLOVIR", "ASPEN PHARMA INDÚSTRIA FARMACÊUTICA LTDA", "400 MG COM CT BL AL PLAS INC X 70", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 331.30));
        medList.add(new Medication("CANCIDAS", "ACETATO DE CASPOFUNGINA", "ERCK SHARP & DOHME FARMACEUTICA  LTDA", "70 MG PO LIOF SOL INJ CT FA VD INC", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 4247.26));
        medList.add(new Medication("NOLUPRON DEPOTME", "ACETATO DE LEUPRORRELINA", "ABBVIE FARMACÊUTICA LTDA", "5,0 MG/ML SOL INJ PT PLA…SER + 15 SACHETS ALCOOL", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 725.23));

        medAdapter = new MedicationAdapter(this, medList);
        medRecView.setAdapter(medAdapter);


        //medSearchBtn = findViewById(R.id.medJsonBtn);
        //jsonContent = findViewById(R.id.jsonText);

        /*medSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
}
