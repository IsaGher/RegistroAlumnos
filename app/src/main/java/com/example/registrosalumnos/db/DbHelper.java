package com.example.registrosalumnos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "registro.db";
    public static final String TABLE_ALUMNOS = "t_alumnos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_ALUMNOS+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "carnet TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "carrera TEXT NOT NULL," +
                "anio_ingreso INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_ALUMNOS);
        onCreate(db);
    }
}
