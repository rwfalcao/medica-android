package com.example.android.pi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedicationSearch extends AppCompatActivity {

    Button medSearchBtn;
    TextView jsonContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_search);

        medSearchBtn = findViewById(R.id.medJsonBtn);
        jsonContent = findViewById(R.id.jsonText);

        medSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
