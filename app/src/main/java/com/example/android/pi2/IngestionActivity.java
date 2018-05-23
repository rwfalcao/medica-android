package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

    Float ingestionScore;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid, schedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingestion);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();

        dependenteId = getIntent().getStringExtra("userid");

        username = findViewById(R.id.ingestUsername);
        medname = findViewById(R.id.ingestMedname);
        schedId = getIntent().getStringExtra("schedId");

        DatabaseReference rotinasRef = database.getReference("/Users/"+uid+"/Dependentes/"+dependenteId+"/Rotinas/"+schedId+"/Ingestoes");

        username.setText(getIntent().getStringExtra("username"));
        medname.setText(getIntent().getStringExtra("medname"));

        ratingBar = findViewById(R.id.IngestRating);

        ingestionButton = findViewById(R.id.ingestionButton);

        ingestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingestionScore = ratingBar.getRating();

                Log.d("QuickNotesMainActivity", String.valueOf(ingestionScore));
            }
        });


    }
}
