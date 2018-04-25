package com.example.android.pi2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
    EditText edtAcorda;
    EditText edtDorme;

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
        edtAcorda = findViewById(R.id.editAcorda);
        edtDorme = findViewById(R.id.editDorme);


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



        btn = findViewById(R.id.btnGravar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
            }
        });



    }

    private void gravar(){
        String nome = edtNome.getText().toString();
        String dataNascimento = edtDataNascimento.getText().toString();
        String sexo = edtSexo.getText().toString();
        String acorda = edtAcorda.getText().toString();
        String dorme = edtDorme.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        String uid = user.getUid();

        DatabaseReference alunos = database.getReference("/Alunos");
        DatabaseReference root = database.getReference("/");
        root.child("Users");
        DatabaseReference users = database.getReference("/Users");
        users.child(uid).child("Nome").setValue(nome);
        users.child(uid).child("Data Nascimento").setValue(dataNascimento);
        users.child(uid).child("Sexo").setValue(sexo);
        users.child(uid).child("Hora Acorda").setValue(acorda);
        users.child(uid).child("Hora Dorme").setValue(dorme);

        startActivity(new Intent(Redirect.this, MenuActivity.class));
    }






}
