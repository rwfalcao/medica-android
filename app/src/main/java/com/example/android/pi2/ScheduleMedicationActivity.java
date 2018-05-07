package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

public class ScheduleMedicationActivity extends AppCompatActivity {

    EditText filterText;
    List<Medication> medList;
    MedicationAdapter medAdapter;
    RecyclerView medRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_medication);

        filterText = findViewById(R.id.ChoosefilterText);

        medList = new ArrayList<>();

        medRecView = findViewById(R.id.scheduleMedicationSearch);
        medRecView.setHasFixedSize(true);
        medRecView.setLayoutManager(new LinearLayoutManager(this));

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
                medAdapter = new MedicationAdapter(ScheduleMedicationActivity.this, medList, getIntent());
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

                        medAdapter = new MedicationAdapter(ScheduleMedicationActivity.this, tempList, getIntent());
                        medRecView.setAdapter(medAdapter);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
