package com.example.clinicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Investigations.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "investigations";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "investigation_name";
    private static final String COLUMN_PRICE = "investigation_price";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // seeding database

        String[] names = {"Analiza Hematologie", "Alergotest cantitativ pediatric", "Factor reumatoid semicantitativ"};
        int[] prices = {100, 220, 10};
        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, "Analiza Hematologie");
//        values.put(COLUMN_PRICE, 60);
        for(int i = 0; i < names.length; i++) {
            values.put(COLUMN_NAME, names[i]);
            values.put(COLUMN_PRICE, prices[i]);
            db.insert(TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }


}
