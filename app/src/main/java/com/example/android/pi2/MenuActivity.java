package com.example.android.pi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    ImageButton meds;
    ImageButton rotinas;
    ImageButton users;
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



        meds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MedicationSearch.class));
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, Redirect.class));
            }
        });

        rotinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Não implementado!", Toast.LENGTH_LONG).show();
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
