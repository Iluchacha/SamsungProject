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

import java.util.ArrayList;

public class ClinicActivity extends AppCompatActivity {

    ListView clinicList;
    Button addClinic, deleteClinic, backClinic;

    dbUpdate mDBConnector;
    Context mContext;
    myClinicAdapter myAdapter;

    int ADD_ACTIVITY = 0;
    int UPDATE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        clinicList = findViewById(R.id.clinicList);
        addClinic = findViewById(R.id.addClinic);
        deleteClinic = findViewById(R.id.deleteClinic);
        backClinic = findViewById(R.id.backClinic);

        Intent i = new Intent(ClinicActivity.this, AddActivity.class);

        mContext = this;
        mDBConnector = new dbUpdate(this);
        myAdapter = new myClinicAdapter(mContext, mDBConnector.cselectAll());
        clinicList.setAdapter(myAdapter);
        updateList();

        backClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBConnector.cdeleteAll();
                updateList();
            }
        });

        addClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(i, 2);
            }
        });

        clinicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Clinic md = myAdapter.getArrayMyData().get(position);
                i.putExtra("updClinic", md);
                startActivityForResult(i, UPDATE_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Clinic md = (Clinic) data.getExtras().getSerializable("Clinic");
            if (requestCode == UPDATE_ACTIVITY) {
                mDBConnector.cupdate(md);
            }
            else
                mDBConnector.cinsert(md.getName(), md.getAddress(), md.getPhone());
            updateList();
        }
    }


    private void updateList () {
        myAdapter.setArrayMyData(mDBConnector.cselectAll());
        myAdapter.notifyDataSetChanged();
    }
}

class myClinicAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Clinic> arrayMyMatches;

    public myClinicAdapter(Context ctx, ArrayList<Clinic> arr) {
        mLayoutInflater = LayoutInflater.from(ctx);
        setArrayMyData(arr);
    }

    public ArrayList<Clinic> getArrayMyData() {
        return arrayMyMatches;
    }

    public void setArrayMyData(ArrayList<Clinic> arrayMyData) {
        this.arrayMyMatches = arrayMyData;
    }

    public int getCount() {
        return arrayMyMatches.size();
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {
        Clinic md = arrayMyMatches.get(position);
        if (md != null) {
            return md.getId();
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.item, null);

        TextView vDate = (TextView) convertView.findViewById(R.id.Date);
        TextView vName = (TextView) convertView.findViewById(R.id.Name);
        Clinic md = arrayMyMatches.get(position);
        String phone = md.getPhone();

        vName.setText(md.getName() + " ");
        vDate.setText(" " + phone);
        //vNote.setText(md.getStatus().toString());
        //vNote.setText(md.getNote());

        return convertView;
    }
}