package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ClinicActivity extends AppCompatActivity {

    ListView clinicList;
    Button addClinic, backClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        clinicList = findViewById(R.id.clinicList);
        addClinic = findViewById(R.id.addClinick);
        backClinic = findViewById(R.id.backClinic);

        Intent i = new Intent(ClinicActivity.this, AddActivity.class);

        backClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(i, 1);
            }
        });



    }
}