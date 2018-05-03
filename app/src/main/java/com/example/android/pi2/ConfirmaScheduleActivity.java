package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.adapters.MedicationAdapter;
import com.example.android.adapters.QuantidadeAdapter;
import com.example.android.models.Medication;
import com.example.android.util.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfirmaScheduleActivity extends AppCompatActivity {

    EditText filterText;
    List<Medication> medList;
    MedicationAdapter medAdapter;
    RecyclerView medRecView;

    ExpandableListView qtdListView;
    List<String> listHeader;
    HashMap<String, List<String>> hashMap;
    QuantidadeAdapter qtdAdapter;
    TextView qtdText;



    String qtdDoses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_schedule);

        qtdText = findViewById(R.id.qtdHeader);
        filterText = findViewById(R.id.filterText);

        medList = new ArrayList<>();

        medRecView = findViewById(R.id.scheduleMedicationSearch);
        medRecView.setHasFixedSize(true);
        medRecView.setLayoutManager(new LinearLayoutManager(this));

        qtdDoses = "0";
        qtdListView = (ExpandableListView) findViewById(R.id.qtdDiariaListView);
        listHeader = new ArrayList<>();
        hashMap = new HashMap<>();

        qtdAdapter = new QuantidadeAdapter(this, listHeader, hashMap);
        qtdListView.setAdapter(qtdAdapter);

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
                medAdapter = new MedicationAdapter(ConfirmaScheduleActivity.this, medList);
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
                            if(tempMed.getNome().toLowerCase().startsWith(query.toLowerCase())){
                                tempList.add(tempMed);
                            }else if(query.matches("")){
                                tempList = medList;
                            }
                        }

                        medAdapter = new MedicationAdapter(ConfirmaScheduleActivity.this, tempList);
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

        iniciarConteudo();

        qtdListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                qtdDoses = hashMap.get(listHeader.get(i)).get(i1).substring(0,1);
                Toast.makeText(ConfirmaScheduleActivity.this, qtdDoses, Toast.LENGTH_LONG).show();
                //qtdText.setText(hashMap.get(listHeader.get(i)).get(i1));
                qtdListView.collapseGroup(i);

                return false;
            }
        });
    }

    public void iniciarConteudo(){
        List<String> lista1 = new ArrayList<>();
        lista1.add("1 vez por dia");
        lista1.add("2 vezes por dia");
        lista1.add("3 vezes por dia");
        lista1.add("4 vezes por dia");
        lista1.add("5 vezes por dia");


        listHeader.add("Doses diárias");


        hashMap.put(listHeader.get(0), lista1);


        qtdAdapter.notifyDataSetChanged();
    }
}
