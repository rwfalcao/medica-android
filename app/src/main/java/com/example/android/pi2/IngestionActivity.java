package com.example.android.pi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.models.Ingestion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class IngestionActivity extends AppCompatActivity {

    TextView username;
    TextView medname;
    String dependenteId;
    RatingBar ratingBar;
    Button ingestionButton;
    EditText editObs;

    Float ingestionScore;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid, schedId, obs, ingesttime, ingestDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingestion);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();

        dependenteId = getIntent().getStringExtra("userid");

        ingesttime = getIntent().getStringExtra("ingestTime");
        ingestDate = getIntent().getStringExtra("ingestDate");

        username = findViewById(R.id.ingestUsername);
        medname = findViewById(R.id.ingestMedname);
        schedId = getIntent().getStringExtra("schedId");
        editObs = findViewById(R.id.ingestObs);

        final DatabaseReference rotinasRef = database.getReference("/Users/"+uid+"/Dependentes/"+dependenteId+"/Rotinas/"+schedId);

        username.setText(getIntent().getStringExtra("username"));
        medname.setText(getIntent().getStringExtra("medname"));

        ratingBar = findViewById(R.id.IngestRating);

        ingestionButton = findViewById(R.id.ingestionButton);

        ingestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingestionScore = ratingBar.getRating();
                obs = editObs.getText().toString();
                Ingestion ingest = new Ingestion(ingestionScore.toString(), ingesttime, ingestDate, obs);

                String rotinaId = rotinasRef.push().getKey();

                rotinasRef.child("Ingestoes").child(rotinaId).setValue(ingest);

                startActivity(new Intent(IngestionActivity.this, MainActivity.class));

            }
        });


    }
}
