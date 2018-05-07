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

    String userId;
    String medDesc;
    String medAtivo;
    String medPreco;

    ExpandableListView qtdListView;
    List<String> listHeader;
    HashMap<String, List<String>> hashMap;
    QuantidadeAdapter qtdAdapter;
    TextView qtdText;

    TextView userName;
    TextView medName;

    String qtdDoses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_schedule);

        qtdText = findViewById(R.id.qtdHeader);

        userName = findViewById(R.id.scheduleUserName);
        medName = findViewById(R.id.scheduleMedName);

        userId = getIntent().getStringExtra("userId");
        medDesc = getIntent().getStringExtra("desc");
        medAtivo = getIntent().getStringExtra("ativo");
        medPreco = getIntent().getStringExtra("preco");

        userName.setText(getIntent().getStringExtra("userName"));
        medName.setText(getIntent().getStringExtra("medName"));


        qtdDoses = "0";
        qtdListView = (ExpandableListView) findViewById(R.id.qtdDiariaListView);
        listHeader = new ArrayList<>();
        hashMap = new HashMap<>();

        qtdAdapter = new QuantidadeAdapter(this, listHeader, hashMap);
        qtdListView.setAdapter(qtdAdapter);



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
