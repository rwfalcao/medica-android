package com.example.android.pi2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.models.BasicTime;
import com.example.android.models.Ingestion;
import com.example.android.models.Schedule;
import com.example.android.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleStatsActivity extends AppCompatActivity {

    TextView schedNome;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid, userId, schedId, dbPath, freq, medName;
    String idate, obs, score, time, conf;

    List<Ingestion> ingestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_stats);

        ingestList = new ArrayList<>();

        schedNome = findViewById(R.id.schedNome);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();

        userId = getIntent().getStringExtra("userId");
        schedId = getIntent().getStringExtra("schedId");

        dbPath = "/Users/"+uid+"/Dependentes/"+userId+"/Rotinas/"+schedId;
        DatabaseReference rotina = database.getReference(dbPath);

        rotina.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                freq = (String) dataSnapshot.child("Frequencia").getValue();
                medName = (String) dataSnapshot.child("Medication").child("nome").getValue();

                for(DataSnapshot ids : dataSnapshot.child("Ingestoes").getChildren()){
                    score = (String) ids.child("score").getValue();
                    idate = (String) ids.child("date").getValue();
                    time = (String) ids.child("time").getValue();
                    obs = (String) ids.child("obs").getValue();
                    conf = (String) ids.child("conf").getValue();

                    Ingestion ing = new Ingestion(score, time, idate, obs, conf);
                    ingestList.add(ing);
                }

                int x = 0;





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        GraphView graph = (GraphView) findViewById(R.id.schedGraph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1.5),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 4)
        });
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                if(data.getY() < 2){
                    return Color.rgb(230,85,65);
                }
                else if(data.getY() >= 2 && data.getY() < 3){
                    return Color.rgb(230,181,65);
                }
                else if(data.getY() >= 3 && data.getY() < 4){
                    return Color.rgb(175,230,65);
                }
                else if(data.getY() >= 4 && data.getY() <= 5){
                    return Color.rgb(96,230,65);
                }else{
                    return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                }
            }
        });
        series.setSpacing(25);
        series.setDrawValuesOnTop(true);
        graph.addSeries(series);
    }
}
