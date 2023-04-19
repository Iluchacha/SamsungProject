package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etClinic, etAddress, etPhone;
    Button btnAddClinic, btnBackClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etClinic = findViewById(R.id.etClinic);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        btnAddClinic = findViewById(R.id.btnAddClinic);
        btnBackClinic = findViewById(R.id.btnBackClinic);


        btnBackClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}