package com.example.android.pi2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Redirect extends AppCompatActivity {

    EditText edtNome;
    EditText edtDataNascimento;
    EditText edtSexo;
    Button medsBtn;
    Button btnLogoff;
    Button btn;


    FirebaseDatabase database;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        edtNome = findViewById(R.id.edtNome);
        edtDataNascimento = findViewById(R.id.edtDataNasc);
        edtSexo = findViewById(R.id.edtSexo);


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        medsBtn = findViewById(R.id.btnMeds);
        medsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medication();
            }
        });

        btn = findViewById(R.id.btnGravar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
            }
        });

        btnLogoff = findViewById(R.id.btnLogoff);
        btnLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoff();
            }
        });


    }

    private void gravar(){
        String nome = edtNome.getText().toString();
        String dataNascimento = edtDataNascimento.getText().toString();
        String sexo = edtSexo.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        String uid = user.getUid();

        DatabaseReference alunos = database.getReference("/Alunos");
        alunos.child(uid).child("Nome").setValue(nome);
        alunos.child(uid).child("Data Nascimento").setValue(dataNascimento);
        alunos.child(uid).child("Sexo").setValue(sexo);
    }

    private void logoff(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(Redirect.this, MainActivity.class));

    }

    private void medication(){
        startActivity(new Intent(Redirect.this, MedicationSearch.class));
    }
}
