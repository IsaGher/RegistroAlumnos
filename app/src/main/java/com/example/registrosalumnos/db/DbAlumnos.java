package com.example.registrosalumnos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

public class DbAlumnos extends DbHelper{

    Context context;

    public DbAlumnos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAlumno(String carnet, String nombre, String carrera, int anio_ingreso){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("carnet", carnet);
            values.put("nombre", nombre);
            values.put("carrera", carrera);
            values.put("anio_ingreso", anio_ingreso);

            id = db.insert(TABLE_ALUMNOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

}
