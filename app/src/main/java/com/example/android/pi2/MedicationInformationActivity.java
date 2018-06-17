package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import java.text.NumberFormat;

public class MedicationInformationActivity extends AppCompatActivity {

    TextView medInfoName, medInfoAtivo, medInfoDesc, medInfoClass, medInfoLab, medInfoPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_information);

        medInfoName = findViewById(R.id.medInfoName);
        medInfoAtivo = findViewById(R.id.medInfoAtivo);
        medInfoDesc = findViewById(R.id.medInfoDesc);
        medInfoClass = findViewById(R.id.medInfoClass);
        medInfoLab = findViewById(R.id.MedInfoLab);
        medInfoPreco = findViewById(R.id.medInfoPreco);


        medInfoName.setText(getIntent().getStringExtra("medName"));
        medInfoAtivo.setText(getIntent().getStringExtra("ativo"));
        medInfoDesc.setText(getIntent().getStringExtra("desc"));
        medInfoClass.setText(getIntent().getStringExtra("tClass"));
        medInfoLab.setText(getIntent().getStringExtra("lab"));

        double price = Double.parseDouble(getIntent().getStringExtra("preco"));

        if(price == 0){
            medInfoPreco.setText("Indisponível em farmácias");
            medInfoPreco.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        }else{

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String preco = formatter.format(price).replace(".", ",");

            medInfoPreco.setText("R"+preco);
        }








    }
}
