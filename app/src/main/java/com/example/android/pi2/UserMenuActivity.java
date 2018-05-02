package com.example.android.pi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.models.User;

public class UserMenuActivity extends AppCompatActivity {

    Button pessoal;
    Button dependente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        pessoal = findViewById(R.id.btnCadPessoal);
        dependente = findViewById(R.id.btnCadDependente);

        pessoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMenuActivity.this, CadastroUser.class));
            }
        });

        dependente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMenuActivity.this, CadastroDependente.class));
            }
        });
    }
}
