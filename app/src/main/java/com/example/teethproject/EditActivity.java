package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    EditText etTeeth, etNote;
    CalendarView clndTeeth;
    Button btnAddEdit, btnBackEdit;
    Spinner spStatus;
    String date = "None";

    Date selDate;

    private Context context;
    private long MyTeethID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTeeth = findViewById(R.id.etTeeth);
        etNote = findViewById(R.id.etNote);
        clndTeeth = findViewById(R.id.clndTeeth);
        btnAddEdit = findViewById(R.id.btnAddEdit);
        btnBackEdit = findViewById(R.id.btnBackEdit);
        spStatus = findViewById(R.id.spStatus);
        if (getIntent().hasExtra("Teeth")){
            Teeth teeth = (Teeth) getIntent().getSerializableExtra("Teeth");
            MyTeethID = teeth.getId();
        }
        else if (getIntent().hasExtra("updTeeth")){
            Teeth teeth = (Teeth) getIntent().getSerializableExtra("updTeeth");
            etTeeth.setText(teeth.getName());
            etNote.setText(teeth.getNote());
            spStatus.setSelection(teeth.getStatus());
            MyTeethID = teeth.getId();
            // clndTeeth.setDate();
        }
        else
        {
            MyTeethID = -1;
        }

        clndTeeth.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selDate = calendar.getTime();
            }
        });

        btnAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etTeeth.getText().toString();
                String note = etNote.getText().toString();

                if (selDate == null) {
                    long selectedDate = clndTeeth.getDate();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(selectedDate);
                    DateFormat dateForm = DateFormat.getDateInstance(DateFormat.DEFAULT);
                    date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                }
                else{
                    DateFormat dateForm = DateFormat.getDateInstance(DateFormat.DEFAULT);
                    date = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
                }
                int status = spStatus.getSelectedItemPosition();
                Teeth matches = new Teeth(MyTeethID, getIntent().getIntExtra("tId", 0), name, date, note, status);
                Intent intent = getIntent();
                intent.putExtra("Teeth", matches);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnBackEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}