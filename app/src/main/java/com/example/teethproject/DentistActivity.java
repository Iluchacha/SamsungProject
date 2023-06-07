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

public class DentistActivity extends AppCompatActivity {

    ListView dentistList;
    Button addDentist, deleteDentist, backDentist;

    dbUpdate mDBConnector;
    Context mContext;
    myDentistAdapter myAdapter;

    int ADD_ACTIVITY = 0;
    int UPDATE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist);

        dentistList = findViewById(R.id.dentistList);
        addDentist = findViewById(R.id.addDentist);
        deleteDentist = findViewById(R.id.deleteDentist);
        backDentist = findViewById(R.id.backDentist);

        Intent i = new Intent(DentistActivity.this, AddDentistActivity.class);

        mContext = this;
        mDBConnector = new dbUpdate(this);
        myAdapter = new myDentistAdapter(mContext, mDBConnector.dselectAll());
        dentistList.setAdapter(myAdapter);
        updateList();

        backDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBConnector.ddeleteAll();
                updateList();
            }
        });

        addDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(i, 3);
            }
        });

        dentistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dentist md = myAdapter.getArrayMyData().get(position);
                i.putExtra("updDentist", md);
                startActivityForResult(i, UPDATE_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Dentist md = (Dentist) data.getExtras().getSerializable("Dentist");
            if (requestCode == UPDATE_ACTIVITY) {
                mDBConnector.dupdate(md);
            }
            else
                mDBConnector.dinsert(md.getName(), md.getClinic(), md.getPhone());
            updateList();
        }
    }


    private void updateList () {
        myAdapter.setArrayMyData(mDBConnector.dselectAll());
        myAdapter.notifyDataSetChanged();
    }
}

class myDentistAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Dentist> arrayMyMatches;

    public myDentistAdapter(Context ctx, ArrayList<Dentist> arr) {
        mLayoutInflater = LayoutInflater.from(ctx);
        setArrayMyData(arr);
    }

    public ArrayList<Dentist> getArrayMyData() {
        return arrayMyMatches;
    }

    public void setArrayMyData(ArrayList<Dentist> arrayMyData) {
        this.arrayMyMatches = arrayMyData;
    }

    public int getCount() {
        return arrayMyMatches.size();
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {
        Dentist md = arrayMyMatches.get(position);
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
        Dentist md = arrayMyMatches.get(position);
        String clinic = md.getClinic();

        vDate.setText(" " + md.getName());
        //vDate.setText(" " + clinic);
        //vNote.setText(md.getStatus().toString());
        //vNote.setText(md.getNote());

        return convertView;
    }
}