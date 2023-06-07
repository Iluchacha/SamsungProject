package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDentistActivity extends AppCompatActivity {

    EditText etDentistClinic, etDentistName, etDentistPhone;
    Button btnAddDentist, btnBackDentist;
    private long MyDentistID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dentist);

        etDentistClinic = findViewById(R.id.etDentistClinic);
        etDentistName = findViewById(R.id.etDentistName);
        etDentistPhone = findViewById(R.id.etDentistPhone);
        btnAddDentist = findViewById(R.id.btnAddDentist);
        btnBackDentist = findViewById(R.id.btnBackDentist);

        if (getIntent().hasExtra("Dentist")){
            Dentist dentist = (Dentist) getIntent().getSerializableExtra("Dentist");
            MyDentistID = dentist.getId();
        }
        else if (getIntent().hasExtra("updDentist")){
            Dentist dentist = (Dentist) getIntent().getSerializableExtra("updDentist");
            etDentistName.setText(dentist.getName());
            etDentistClinic.setText(dentist.getClinic());
            etDentistPhone.setText(dentist.getPhone());
            MyDentistID = dentist.getId();
            // clndTeeth.setDate();
        }
        else
        {
            MyDentistID = -1;
        }

        btnAddDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etDentistName.getText().toString();
                String clinic = etDentistClinic.getText().toString();
                String phone = etDentistPhone.getText().toString();
                Dentist matches = new Dentist(MyDentistID, name, clinic, phone);
                Intent intent = getIntent();
                intent.putExtra("Dentist", matches);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnBackDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}