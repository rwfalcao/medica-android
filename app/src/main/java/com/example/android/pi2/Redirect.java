package com.example.android.pi2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Redirect extends AppCompatActivity {

    EditText edtNome;
    EditText edtSobrenome;

    TextView hrAcorda;
    TextView hrDorme;

    Calendar currentTime;
    int hora, minuto;

    EditText edtAcorda;
    EditText edtDorme;

    Button btnLogoff;
    Button btn;

    String sexo;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    RadioButton masc,fem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        edtNome = findViewById(R.id.edtNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);

        hrAcorda = findViewById(R.id.horaAcorda);
        hrDorme = findViewById(R.id.horaDorme);

        //edtAcorda = findViewById(R.id.editAcorda);
        //edtDorme = findViewById(R.id.editDorme);
        final int hora = currentTime.get(Calendar.HOUR_OF_DAY);
        final int minuto = currentTime.get(Calendar.MINUTE);


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        masc = findViewById(R.id.radioMasc);
        fem = findViewById(R.id.radioFem);

        masc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexo = "Masculino";
            }
        });

        fem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexo = "Ferminino";
            }
        });
/*
        hrAcorda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog picker = new TimePickerDialog(Redirect.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hrAcorda.setText(i+" : "+i1);
                    }
                },hora, minuto,true);
                picker.show();
            }
        });*/


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
        String sobrenome = edtSobrenome.getText().toString();

        String acorda = edtAcorda.getText().toString();
        String dorme = edtDorme.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        String uid = user.getUid();

        DatabaseReference alunos = database.getReference("/Alunos");
        DatabaseReference root = database.getReference("/");
        root.child("Users");
        DatabaseReference users = database.getReference("/Users");

        if(!TextUtils.isEmpty(nome)){
            User user_insert = new User(nome,sobrenome,sexo,acorda,dorme);
            users.child(uid).setValue(user_insert);
            Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Campo obrigatório", Toast.LENGTH_LONG).show();
        }
        /*
        users.child(uid).child("Nome").setValue(nome);
        users.child(uid).child("Sobrenome").setValue(sobrenome);
        users.child(uid).child("Sexo").setValue(sexo);
        users.child(uid).child("Hora Acorda").setValue(acorda);
        users.child(uid).child("Hora Dorme").setValue(dorme);
        */

        //startActivity(new Intent(Redirect.this, MenuActivity.class));
    }






}
