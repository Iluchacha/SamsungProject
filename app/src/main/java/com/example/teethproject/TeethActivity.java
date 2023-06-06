package com.example.teethproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TeethActivity extends AppCompatActivity {

    dbUpdate mDBConnector;
    Context mContext;
    Button btnBack, btnDeleteAll, btnAdd;
    TextView teethTxt;
    ListView teethList;
    myListAdapter myAdapter;

    int ADD_ACTIVITY = 0;
    int UPDATE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth);

        btnBack = findViewById(R.id.btnBack);
        teethTxt = findViewById(R.id.teethTxt);
        teethList = findViewById(R.id.teethList);
        btnAdd = findViewById(R.id.btnAdd);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        //btnDeleteTable = findViewById(R.id.btnDeleteTable);

        teethTxt.setText(getIntent().getStringExtra("teethName"));
        Intent i = new Intent(TeethActivity.this, MainActivity.class);
        Intent j = new Intent(TeethActivity.this, EditActivity.class);
        int teethId = getIntent().getIntExtra("teethId", 0);

        mContext = this;
        mDBConnector = new dbUpdate(this);
        myAdapter = new myListAdapter(mContext, mDBConnector.tselectAll(teethId));
        teethList.setAdapter(myAdapter);
        updateList();

//        if (getIntent().hasExtra("Teeth")){
//            Teeth md = (Teeth) getIntent().getExtras().getSerializable("Teeth");
//            mDBConnector.insert(md.getName(), md.getDate(), md.getNote());
//            //updateList();
//            cursor = mDBConnector.getCursorTeeth();
//            //cursor = dbh.getReadableDatabase().rawQuery("SELECT * FROM teeth", null);
//
//            Toast.makeText(this, "\n\n\n\n" + cursor.getCount() + "\n\n\n\n", Toast.LENGTH_SHORT).show();
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                String name = cursor.getString(1);
//                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
//                cursor.moveToNext();
//            }
//        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(j);
                j.putExtra("tId", teethId);
                startActivityForResult(j, ADD_ACTIVITY);
                updateList();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBConnector.tdeleteAll(teethId);
                updateList();
            }
        });

//        btnDeleteTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDBConnector.deleteTable();
//            }
//        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        teethList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Teeth md = myAdapter.getArrayMyData().get(position);
                j.putExtra("tId", teethId);
                j.putExtra("updTeeth", md);
                startActivityForResult(j, UPDATE_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Teeth md = (Teeth) data.getExtras().getSerializable("Teeth");
            if (requestCode == UPDATE_ACTIVITY) {
                mDBConnector.tupdate(md);
            }
            else
                mDBConnector.tinsert(md.gettId(), md.getName(), md.getDate(), md.getNote(), md.getStatus());
            updateList();
        }
    }


    private void updateList () {
        myAdapter.setArrayMyData(mDBConnector.tselectAll(getIntent().getIntExtra("teethId", 0)));
        myAdapter.notifyDataSetChanged();
    }

}

class myListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Teeth> arrayMyMatches;

    public myListAdapter (Context ctx, ArrayList<Teeth> arr) {
        mLayoutInflater = LayoutInflater.from(ctx);
        setArrayMyData(arr);
    }

    public ArrayList<Teeth> getArrayMyData() {
        return arrayMyMatches;
    }

    public void setArrayMyData(ArrayList<Teeth> arrayMyData) {
        this.arrayMyMatches = arrayMyData;
    }

    public int getCount () {
        return arrayMyMatches.size();
    }

    public Object getItem (int position) {

        return position;
    }

    public long getItemId (int position) {
        Teeth md = arrayMyMatches.get(position);
        if (md != null) {
            return md.getId();
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.item, null);

        TextView vDate = (TextView) convertView.findViewById(R.id.Date);
        TextView vName= (TextView) convertView.findViewById(R.id.Name);
        Teeth md = arrayMyMatches.get(position);
        String date = md.getDate();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 =  simpleDateFormat.parse(md.getDate());
            calendar.setTime(date1);
            DateFormat dateForm = DateFormat.getDateInstance(DateFormat.DEFAULT);
            date = dateForm.format(calendar.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        vName.setText(date + " ");
        vDate.setText(" " + md.getName());
        //vNote.setText(md.getStatus().toString());
        //vNote.setText(md.getNote());

        return convertView;
    }
} // end myAdapter
