package com.example.android.pi2;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUser extends AppCompatActivity {

    /*DECLARAÇÃO DE VIEWS*/

    EditText edtNome;
    EditText edtSobrenome;

    RadioButton masc;
    RadioButton fem;

    Button gravarUser;

    TextView acorda, dorme;

    /*DECLARAÇÃO DE VIEWS*/



    /*DECLARAÇÃO DE UTILIDADES*/

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    /*DECLARAÇÃO DE UTILIDADES*/



    /*OUTRAS DECLARAÇÕES*/

    String sexo;
    String nome;
    String sobrenome;
    String hrAcorda;
    String hrDorme;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        /*FIND DAS VIEWS*/

        edtNome = findViewById(R.id.edtUserName);
        edtSobrenome = findViewById(R.id.edtUserLast);

        masc = findViewById(R.id.rdMasc);
        fem = findViewById(R.id.rdFem);

        acorda = findViewById(R.id.userAcorda);
        dorme = findViewById(R.id.userDorme);

        gravarUser = findViewById(R.id.btnGravarUser);

        /*FIND DAS VIEWS*/

        /*LISTENERS*/

            /*LISTENERS BOTÃO RADIO*/

            masc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sexo = "Masculino";
                }
            });

            fem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sexo = "Feminino";
                }
            });
            /*LISTENERS BOTÃO RADIO*/


            /*LISTENER DAS HORAS*/

            acorda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog picker = new TimePickerDialog(CadastroUser.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            String hora = String.format("%02d", i);
                            String minuto = String.format("%02d", i1);
                            acorda.setText(hora + " : " + minuto);
                        }
                    }, 0, 0, true);

                    picker.show();
                }


            });

            dorme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog picker = new TimePickerDialog(CadastroUser.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            String hora = String.format("%02d", i);
                            String minuto = String.format("%02d", i1);
                            dorme.setText(hora + " : " + minuto);
                        }
                    }, 0,0, true);

                    picker.show();
                }
            });

            /*LISTENER DAS HORAS*/


            /*LISTENER BOTÃO GRAVAR*/
                gravarUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gravaUser();
                    }
                });
            /*LISTENER BOTÃO GRAVAR*/
        /*LISTENERS*/
    }

    public void gravaUser(){
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();

        nome = edtNome.getText().toString().trim();
        sobrenome = edtSobrenome.getText().toString().trim();
        hrAcorda = acorda.getText().toString();
        hrDorme = dorme.getText().toString();

        DatabaseReference users = database.getReference("/Users");

        User user_insert = new User(nome, sobrenome, sexo, hrAcorda, hrDorme);
        users.child(uid).setValue(user_insert);
    }
}
