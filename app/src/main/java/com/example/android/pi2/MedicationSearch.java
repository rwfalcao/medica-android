package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.adapters.MedicationAdapter;
import com.example.android.models.Medication;
import com.example.android.util.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicationSearch extends AppCompatActivity {

    Button medSearchBtn;
    Button filterBtn;
    EditText filterText;
    TextView jsonContent;
    RecyclerView medRecView;
    MedicationAdapter medAdapter;
    List<Medication> medList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_search);

        filterBtn = findViewById(R.id.filterBtn);
        filterText = findViewById(R.id.filterText);

        medList = new ArrayList<>();

        medRecView = findViewById(R.id.medRecycleView);
        medRecView.setHasFixedSize(true);
        medRecView.setLayoutManager(new LinearLayoutManager(this));

        /*medList.add(new Medication("VIROTIN", "ACICLOVIR", "ASPEN PHARMA INDÚSTRIA FARMACÊUTICA LTDA", "400 MG COM CT BL AL PLAS INC X 70", "L02A3 - ANÁLOGOS HORMONA…DOTROFINAS CITOSTÁTICOS", "Não", 331.30));
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
*/

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<List<Medication>> medCall = api.getMeds();

        medCall.enqueue(new Callback<List<Medication>>() {
            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                List<Medication> medications = response.body();
                for(Medication med: medications){
                    double medPreco = med.getPreco();
                    medList.add(new Medication(med.getNome(), med.getpAtivo(), med.getLab(), med.getDesc(), med.gettClass(), med.getResctric(), med.getPreco()));
                }
                medAdapter = new MedicationAdapter(MedicationSearch.this, medList);
                medRecView.setAdapter(medAdapter);

                filterText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String query = filterText.getText().toString();

                        List<Medication> tempList = new ArrayList<>();

                        for(Medication tempMed:medList){
                            if(tempMed.getNome().toLowerCase().contains(query.toLowerCase())){
                                tempList.add(tempMed);
                            }else if(query.matches("")){
                                tempList = medList;
                            }
                        }

                        medAdapter = new MedicationAdapter(MedicationSearch.this, tempList);
                        medRecView.setAdapter(medAdapter);
                    }
                });

                /*PENSAR NISSO*/
                /*filterText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        String query = filterText.getText().toString();

                        List<Medication> tempList = new ArrayList<>();

                        for(Medication tempMed:medList){
                            if(tempMed.getNome().toLowerCase().contains(query.toLowerCase())){
                                tempList.add(tempMed);
                            }else if(query.matches("")){
                                tempList = medList;
                            }
                        }

                        medAdapter = new MedicationAdapter(MedicationSearch.this, tempList);
                        medRecView.setAdapter(medAdapter);


                        return false;
                    }
                });*/
                /*PENSAR NISSO*/
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });








        //medSearchBtn = findViewById(R.id.medJsonBtn);
        //jsonContent = findViewById(R.id.jsonText);

        /*medSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        /*PENSAR NISSO*/
        filterText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String query = filterText.getText().toString();

                List<Medication> tempList = new ArrayList<>();


                return false;
            }
        });
        /*PENSAR NISSO*/

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medList.remove(medList.size() - 1);
                medAdapter = new MedicationAdapter(MedicationSearch.this, medList);
                medRecView.setAdapter(medAdapter);
            }
        });
    }
}
