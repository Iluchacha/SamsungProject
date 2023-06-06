package com.example.teethproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbUpdate {

    private static final String DATABASE_NAME = "teeth.db";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_TNAME = "teeth";
    private static final String TABLE_CNAME = "clinic";
    private static final String TABLE_DNAME = "dentist";

    private static final String TCOLUMN_ID = "id";
    private static final String TCOLUMN_TID = "tId";
    private static final String TCOLUMN_NAME = "Name";
    private static final String TCOLUMN_DATE = "Date";
    private static final String TCOLUMN_NOTE = "Note";

    private static final String TCOLUMN_STATUS = "Status";


    private static final int TNUM_COLUMN_ID = 0;
    private static final int TNUM_COLUMN_TID = 1;
    private static final int TNUM_COLUMN_NAME = 2;
    private static final int TNUM_COLUMN_DATE = 3;
    private static final int TNUM_COLUMN_NOTE = 4;
    private static final int TNUM_COLUMN_STATUS = 5;

    private static final String CCOLUMN_ID = "id";
    private static final String CCOLUMN_NAME = "Name";
    private static final String CCOLUMN_ADDRESS = "Address";
    private static final String CCOLUMN_PHONE = "Phone";

    private static final int CNUM_COLUMN_ID = 0;
    private static final int CNUM_COLUMN_NAME = 1;
    private static final int CNUM_COLUMN_ADDRESS = 2;
    private static final int CNUM_COLUMN_PHONE = 3;

    private static final String DCOLUMN_ID = "id";
    private static final String DCOLUMN_NAME = "Name";
    private static final String DCOLUMN_CLINIC = "Clinic";
    private static final String DCOLUMN_PHONE = "Phone";

    private static final int DNUM_COLUMN_ID = 0;
    private static final int DNUM_COLUMN_NAME = 1;
    private static final int DNUM_COLUMN_CLINIC = 2;
    private static final int DNUM_COLUMN_PHONE = 3;

    private OpenHelper mOpenHelper;

    private SQLiteDatabase mDataBase;

    public dbUpdate(Context context) {
        mOpenHelper = new OpenHelper(context, DATABASE_NAME, DATABASE_VERSION);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long tinsert(int tId, String name, String date, String note, int status) {
        ContentValues cv = new ContentValues();
        cv.put(TCOLUMN_TID, tId);
        cv.put(TCOLUMN_NAME, name);
        cv.put(TCOLUMN_DATE, date);
        cv.put(TCOLUMN_NOTE, note);
        cv.put(TCOLUMN_STATUS, status);
        return mDataBase.insert(TABLE_TNAME, null, cv);
    }

    public long cinsert(String name, String address, String phone) {
        ContentValues cv = new ContentValues();
        cv.put(CCOLUMN_NAME, name);
        cv.put(CCOLUMN_ADDRESS, address);
        cv.put(CCOLUMN_PHONE, phone);
        return mDataBase.insert(TABLE_CNAME, null, cv);
    }

    public long dinsert(String name, String clinic, String phone) {
        ContentValues cv = new ContentValues();
        cv.put(DCOLUMN_NAME, name);
        cv.put(DCOLUMN_CLINIC, clinic);
        cv.put(DCOLUMN_PHONE, phone);
        return mDataBase.insert(TABLE_DNAME, null, cv);
    }

    public int tupdate(Teeth md) {
        ContentValues cv = new ContentValues();
        cv.put(TCOLUMN_ID, md.getId());
        cv.put(TCOLUMN_TID, md.gettId());
        cv.put(TCOLUMN_NAME, md.getName());
        cv.put(TCOLUMN_DATE, md.getDate());
        cv.put(TCOLUMN_NOTE, md.getNote());
        cv.put(TCOLUMN_STATUS, md.getStatus());
        return mDataBase.update(TABLE_TNAME, cv, TCOLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public int cupdate(Clinic md) {
        ContentValues cv = new ContentValues();
        cv.put(CCOLUMN_ID, md.getId());
        cv.put(CCOLUMN_NAME, md.getName());
        cv.put(CCOLUMN_ADDRESS, md.getAddress());
        cv.put(CCOLUMN_PHONE, md.getPhone());
        return mDataBase.update(TABLE_CNAME, cv, CCOLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public int dupdate(Dentist md) {
        ContentValues cv = new ContentValues();
        cv.put(DCOLUMN_ID, md.getId());
        cv.put(DCOLUMN_NAME, md.getName());
        cv.put(DCOLUMN_CLINIC, md.getClinic());
        cv.put(DCOLUMN_PHONE, md.getPhone());
        return mDataBase.update(TABLE_DNAME, cv, DCOLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void tdeleteAll(int nId) {
        mDataBase.delete(TABLE_TNAME + " WHERE tId = " + nId, null, null);
    }

    public void cdeleteAll() {
        mDataBase.delete(TABLE_CNAME, null, null);
    }

    public void ddeleteAll() {
        mDataBase.delete(TABLE_DNAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_TNAME, TCOLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Cursor getCursorTeeth(){
        return  mOpenHelper.getReadableDatabase().rawQuery("SELECT * FROM teeth", null);
    }

    public Teeth select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_TNAME, null, TCOLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        int tId = mCursor.getInt(TNUM_COLUMN_TID);
        String Name = mCursor.getString(TNUM_COLUMN_NAME);
        String Date = mCursor.getString(TNUM_COLUMN_DATE);
        String Note = mCursor.getString(TNUM_COLUMN_NOTE);
        int Status = mCursor.getInt(TNUM_COLUMN_STATUS);
        return new Teeth(id, tId, Name, Date, Note, Status);
    }

    public void deleteTable(){

        mDataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_TNAME);
        //mOpenHelper.onCreate(mDataBase);
        String query = "CREATE TABLE " + TABLE_TNAME + " (" +
                TCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TCOLUMN_TID + " INTEGER, " +
                TCOLUMN_NAME + " TEXT, " +
                TCOLUMN_DATE + " TEXT, " +
                TCOLUMN_NOTE + " TEXT, " +
                TCOLUMN_STATUS + " INTEGER);";
        mDataBase.execSQL(query);

    }

    public int getStatus(int id) {
        //Cursor mCursor = mDataBase.rawQuery("SELECT TOP 1 Status FROM teeth WHERE tId = " + id + " ORDER BY id", null);
        Cursor mCursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_TNAME +" WHERE tId = " + id + " ORDER BY id DESC LIMIT 1", null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            return mCursor.getInt(TNUM_COLUMN_STATUS);
        }
        return -1;
    }

    public ArrayList<Teeth> tselectAll(int nId) {
        Cursor mCursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_TNAME + " WHERE tId = " + nId + " ORDER BY Date DESC", null);

        ArrayList<Teeth> arr = new ArrayList<Teeth>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(TNUM_COLUMN_ID);
                int tId = mCursor.getInt(TNUM_COLUMN_TID);
                String name = mCursor.getString(TNUM_COLUMN_NAME);
                String date = mCursor.getString(TNUM_COLUMN_DATE);
                String note = mCursor.getString(TNUM_COLUMN_NOTE);
                int status = mCursor.getInt(TNUM_COLUMN_STATUS);
                arr.add(new Teeth(id, tId, name, date, note, status));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Clinic> cselectAll() {
        Cursor mCursor = mDataBase.query(TABLE_CNAME, null, null, null, null, null, null);

        ArrayList<Clinic> arr = new ArrayList<Clinic>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(CNUM_COLUMN_ID);
                String name = mCursor.getString(CNUM_COLUMN_NAME);
                String address = mCursor.getString(CNUM_COLUMN_ADDRESS);
                String phone = mCursor.getString(CNUM_COLUMN_PHONE);
                arr.add(new Clinic(id, name, address, phone));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Dentist> dselectAll() {
        Cursor mCursor = mDataBase.query(TABLE_DNAME, null, null, null, null, null, null);

        ArrayList<Dentist> arr = new ArrayList<Dentist>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(DNUM_COLUMN_ID);
                String name = mCursor.getString(DNUM_COLUMN_NAME);
                String clinic = mCursor.getString(DNUM_COLUMN_CLINIC);
                String phone = mCursor.getString(DNUM_COLUMN_PHONE);
                arr.add(new Dentist(id, name, clinic, phone));
            } while (mCursor.moveToNext());
        }
        return arr;
    }


}

class OpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_TNAME = "teeth";
    private static final String TABLE_CNAME = "clinic";
    private static final String TABLE_DNAME = "dentist";

    private static final String TCOLUMN_ID = "id";
    private static final String TCOLUMN_TID = "tId";
    private static final String TCOLUMN_NAME = "Name";
    private static final String TCOLUMN_DATE = "Date";
    private static final String TCOLUMN_NOTE = "Note";

    private static final String TCOLUMN_STATUS = "Status";

    private static final String CCOLUMN_ID = "id";
    private static final String CCOLUMN_NAME = "Name";
    private static final String CCOLUMN_ADDRESS = "Address";
    private static final String CCOLUMN_PHONE = "Phone";

    private static final String DCOLUMN_ID = "id";
    private static final String DCOLUMN_NAME = "Name";
    private static final String DCOLUMN_CLINIC = "Clinic";
    private static final String DCOLUMN_PHONE = "Phone";

    OpenHelper(Context context, String DATABASE_NAME, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tquery = "CREATE TABLE IF NOT EXISTS " + TABLE_TNAME +  "(" +
                TCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TCOLUMN_TID + " INTEGER, " +
                TCOLUMN_NAME + " TEXT, " +
                TCOLUMN_DATE + " TEXT, " +
                TCOLUMN_NOTE + " TEXT, " +
                TCOLUMN_STATUS + " INTEGER);";

        String cquery = "CREATE TABLE IF NOT EXISTS " + TABLE_CNAME + " (" +
                CCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CCOLUMN_NAME + " TEXT, " +
                CCOLUMN_ADDRESS + " TEXT, " +
                CCOLUMN_PHONE + " TEXT);";

        String dquery = "CREATE TABLE IF NOT EXISTS " + TABLE_DNAME + " (" +
                DCOLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DCOLUMN_NAME + " TEXT, " +
                DCOLUMN_CLINIC + " TEXT, " +
                DCOLUMN_PHONE + " TEXT);";

        db.execSQL(dquery);
        db.execSQL(cquery);
        db.execSQL(tquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TNAME);
        onCreate(db);
    }
}
