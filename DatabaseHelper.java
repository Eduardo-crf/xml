package com.example.myava2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "veiculos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_VEICULOS = "veiculos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "modelo";
    public static final String COLUMN_DESCRIPTION = "marca";
    public static final String COLUMN_PRICE = "ano";
    public static final String COLUMN_QUANTITY = "quilometragem";
    public static final String COLUMN_CATEGORY = "categoria";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_VEICULOS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_PRICE + " REAL, " +
            COLUMN_QUANTITY + " INTEGER, " +
            COLUMN_CATEGORY + " TEXT" +
            ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEICULOS);
        onCreate(db);
    }
}