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
    private long MyClinicID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etClinic = findViewById(R.id.etClinic);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        btnAddClinic = findViewById(R.id.btnAddClinic);
        btnBackClinic = findViewById(R.id.btnBackClinic);

        if (getIntent().hasExtra("Clinic")){
            Clinic clinic = (Clinic) getIntent().getSerializableExtra("Clinic");
            MyClinicID = clinic.getId();
        }
        else if (getIntent().hasExtra("updClinic")){
            Clinic clinic = (Clinic) getIntent().getSerializableExtra("updClinic");
            etClinic.setText(clinic.getName());
            etAddress.setText(clinic.getAddress());
            etPhone.setText(clinic.getPhone());
            MyClinicID = clinic.getId();
            // clndTeeth.setDate();
        }
        else
        {
            MyClinicID = -1;
        }

        btnAddClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etClinic.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                Clinic matches = new Clinic(MyClinicID, name, address, phone);
                Intent intent = getIntent();
                intent.putExtra("Clinic", matches);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnBackClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}