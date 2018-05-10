package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.adapters.MedicationAdapter;
import com.example.android.adapters.QuantidadeAdapter;
import com.example.android.models.BasicTime;
import com.example.android.models.Medication;
import com.example.android.util.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String userId;
    String hrAcorda;
    String hrDorme;
    String uid;

    String medNome;
    String medAtivo;
    String medLab;
    String medDesc;
    String medtClass;
    String medRestrict;
    String medPreco;

    String qtdDoses;


    ExpandableListView qtdListView;
    List<String> listHeader;
    HashMap<String, List<String>> hashMap;
    QuantidadeAdapter qtdAdapter;

    TextView qtdText;
    TextView userName;
    TextView medName;
    TextView schedFreq;

    Button confirmSched;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_schedule);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();

        qtdText = findViewById(R.id.qtdHeader);

        userName = findViewById(R.id.scheduleUserName);
        medName = findViewById(R.id.scheduleMedName);
        schedFreq = findViewById(R.id.schedFreq);

        confirmSched = findViewById(R.id.btnConfirmSchedule);


        medNome = getIntent().getStringExtra("medName");
        medDesc = getIntent().getStringExtra("desc");
        medAtivo = getIntent().getStringExtra("ativo");
        medPreco = getIntent().getStringExtra("preco");
        medLab = getIntent().getStringExtra("lab");
        medtClass = getIntent().getStringExtra("tClass");
        medRestrict = getIntent().getStringExtra("restric");

        medName.setText(medNome);

        userId = getIntent().getStringExtra("userId");
        hrAcorda = getIntent().getStringExtra("hrAcorda");
        hrDorme = getIntent().getStringExtra("hrDorme");

        userName.setText(getIntent().getStringExtra("userName"));
        schedFreq.setText("Doses diárias");

        qtdDoses = "0";
        qtdListView = (ExpandableListView) findViewById(R.id.qtdDiariaListView);
        listHeader = new ArrayList<>();
        hashMap = new HashMap<>();

        qtdAdapter = new QuantidadeAdapter(this, listHeader, hashMap);
        qtdListView.setAdapter(qtdAdapter);

        confirmSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravaRotina();
            }
        });

        iniciarConteudo();



        qtdListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                qtdDoses = hashMap.get(listHeader.get(i)).get(i1).substring(0,1);
                schedFreq.setText(qtdDoses+" vezes ao dia");
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

    public void gravaRotina(){
        DatabaseReference rotinasRef = database.getReference("/Users/"+uid+"/Dependentes/"+userId+"/Rotinas");

        Medication med = new Medication(medNome, medAtivo, medLab, medDesc, medtClass, medRestrict, Double.parseDouble(medPreco));

        List<BasicTime> timeList = BasicTime.divideTime(hrAcorda, hrDorme, Integer.parseInt(qtdDoses));

        String rotinaId = rotinasRef.push().getKey();

        rotinasRef.child(rotinaId).child("Medicamento").setValue(med);

        rotinasRef.child(rotinaId).child("Horários").setValue(timeList);
    }
}
