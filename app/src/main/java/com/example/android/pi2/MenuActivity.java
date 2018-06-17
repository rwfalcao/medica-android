package com.example.android.pi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MenuActivity extends AppCompatActivity {

    ImageView meds;
    ImageView rotinas;
    ImageView users;
    Button btnLogoff;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        meds = findViewById(R.id.menu_pill);
        rotinas = findViewById(R.id.menu_box);
        users = findViewById(R.id.menu_board);
        btnLogoff = findViewById(R.id.btnLogoff);



        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        final String uid = mAuth.getUid();

        DatabaseReference userRef = database.getReference("/Users/");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(uid)) {
                    startActivity(new Intent(MenuActivity.this, CadastroUser.class));
                    Toast.makeText(getApplicationContext(), "É preciso se informar seus daados antes de usar o aplicativo!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        meds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MedicationSearch.class));
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, UserMenuActivity.class));
            }
        });

        rotinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MedSchedule.class));
            }
        });

        btnLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoff();
            }
        });

    }

    private void logoff() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
    }
}
