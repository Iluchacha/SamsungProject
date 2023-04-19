package com.example.teethproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbUpdate {

    private static final String DATABASE_NAME = "teeth.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "teeth";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TID = "tId";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_NOTE = "Note";

    private static final String COLUMN_STATUS = "Status";


    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TID = 1;
    private static final int NUM_COLUMN_NAME = 2;
    private static final int NUM_COLUMN_DATE = 3;
    private static final int NUM_COLUMN_NOTE = 4;
    private static final int NUM_COLUMN_STATUS = 5;

    private OpenHelper mOpenHelper;

    private SQLiteDatabase mDataBase;

    public dbUpdate(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(int tId, String name, String date, String note, int status) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TID, tId);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_NOTE, note);
        cv.put(COLUMN_STATUS, status);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Teeth md) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, md.getId());
        cv.put(COLUMN_TID, md.gettId());
        cv.put(COLUMN_NAME, md.getName());
        cv.put(COLUMN_DATE, md.getDate());
        cv.put(COLUMN_NOTE, md.getNote());
        cv.put(COLUMN_STATUS, md.getStatus());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void deleteAll(int nId) {
        mDataBase.delete(TABLE_NAME + " WHERE tId = " + nId, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Cursor getCursorTeeth(){
        return  mOpenHelper.getReadableDatabase().rawQuery("SELECT * FROM teeth", null);
    }

    public Teeth select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        int tId = mCursor.getInt(NUM_COLUMN_TID);
        String Name = mCursor.getString(NUM_COLUMN_NAME);
        String Date = mCursor.getString(NUM_COLUMN_DATE);
        String Note = mCursor.getString(NUM_COLUMN_NOTE);
        int Status = mCursor.getInt(NUM_COLUMN_STATUS);
        return new Teeth(id, tId, Name, Date, Note, Status);
    }

    public void deleteTable(){

        mDataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //mOpenHelper.onCreate(mDataBase);
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_NOTE + " TEXT, " +
                COLUMN_STATUS + " INTEGER);";
        mDataBase.execSQL(query);

    }

    public int getStatus(int id) {
        //Cursor mCursor = mDataBase.rawQuery("SELECT TOP 1 Status FROM teeth WHERE tId = " + id + " ORDER BY id", null);
        Cursor mCursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE tId = " + id + " ORDER BY id DESC LIMIT 1", null);

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            return mCursor.getInt(NUM_COLUMN_STATUS);
        }
        return -1;
    }

    public ArrayList<Teeth> selectAll(int nId) {
        Cursor mCursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE tId = " + nId + " ORDER BY Date DESC", null);

        ArrayList<Teeth> arr = new ArrayList<Teeth>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                int tId = mCursor.getInt(NUM_COLUMN_TID);
                String name = mCursor.getString(NUM_COLUMN_NAME);
                String date = mCursor.getString(NUM_COLUMN_DATE);
                String note = mCursor.getString(NUM_COLUMN_NOTE);
                int status = mCursor.getInt(NUM_COLUMN_STATUS);
                arr.add(new Teeth(id, tId, name, date, note, status));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TID + " INTEGER, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_NOTE + " TEXT, " +
                    COLUMN_STATUS + " INTEGER);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}