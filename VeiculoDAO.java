package com.example.myava2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
        DatabaseHelper.COLUMN_ID,
        DatabaseHelper.COLUMN_NAME,
        DatabaseHelper.COLUMN_DESCRIPTION,
        DatabaseHelper.COLUMN_PRICE,
        DatabaseHelper.COLUMN_QUANTITY,
        DatabaseHelper.COLUMN_CATEGORY
    };

    public VeiculoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Veiculo createVeiculo(String modelo, String marca, double ano, int quilometragem, String categoria) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, modelo);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, marca);
        values.put(DatabaseHelper.COLUMN_PRICE, ano);
        values.put(DatabaseHelper.COLUMN_QUANTITY, quilometragem);
        values.put(DatabaseHelper.COLUMN_CATEGORY, categoria);
        long insertId = database.insert(DatabaseHelper.TABLE_VEICULOS, null, values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_VEICULOS, allColumns,
                DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Veiculo newVeiculo = cursorToVeiculo(cursor);
        cursor.close();
        return newVeiculo;
    }

    public void deleteVeiculo(long id) {
        database.delete(DatabaseHelper.TABLE_VEICULOS, DatabaseHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Veiculo> getAllVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_VEICULOS, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Veiculo veiculo = cursorToVeiculo(cursor);
            veiculos.add(veiculo);
            cursor.moveToNext();
        }
        cursor.close();
        return veiculos;
    }

    private Veiculo cursorToVeiculo(Cursor cursor) {
        Veiculo veiculo = new Veiculo(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getDouble(3),
                cursor.getInt(4),
                cursor.getString(5)
        );
        return veiculo;
    }

    public void updateVeiculo(Veiculo veiculo) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, veiculo.getName());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, veiculo.getDescription());
        values.put(DatabaseHelper.COLUMN_PRICE, veiculo.getPrice());
        values.put(DatabaseHelper.COLUMN_QUANTITY, veiculo.getQuantity());
        values.put(DatabaseHelper.COLUMN_CATEGORY, veiculo.getCategory());
        database.update(DatabaseHelper.TABLE_VEICULOS, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(veiculo.getId())});
    }
}