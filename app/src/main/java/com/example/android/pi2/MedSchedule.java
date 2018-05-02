package com.example.android.pi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedSchedule extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String uid;

    List<User> depList;

    Button novaRotina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_schedule);

        novaRotina = findViewById(R.id.btnNovaRotina);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        uid = mAuth.getUid();

        depList = new ArrayList<>();

        novaRotina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedSchedule.this, ChooseUser.class));
            }
        });
    }


}
