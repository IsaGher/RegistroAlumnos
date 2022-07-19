package com.example.registrosalumnos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.registrosalumnos.entidades.Alumnos;

import java.util.ArrayList;

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

    public ArrayList<Alumnos> mostrarAlumnos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alumnos> listaAlumnos = new ArrayList<>();
        Alumnos alumno = null;
        Cursor cursorAlumnos = null;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS, null);

        if(cursorAlumnos.moveToFirst()){
            do{
                alumno = new Alumnos();
                alumno.setId(cursorAlumnos.getInt(0));
                alumno.setCarnet(cursorAlumnos.getString(1));
                alumno.setNombre(cursorAlumnos.getString(2));
                alumno.setCarrera(cursorAlumnos.getString(3));
                alumno.setAnio_ingreso(cursorAlumnos.getInt(4));
                listaAlumnos.add(alumno);
            }while (cursorAlumnos.moveToNext());
        }
        cursorAlumnos.close();
        return listaAlumnos;
    }

    public Alumnos verAlumno(int id){


        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Alumnos alumno = null;
        Cursor cursorAlumnos;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS + " WHERE id =" + id + " LIMIT 1", null);

        if(cursorAlumnos.moveToFirst()){

                alumno = new Alumnos();
                alumno.setId(cursorAlumnos.getInt(0));
                alumno.setCarnet(cursorAlumnos.getString(1));
                alumno.setNombre(cursorAlumnos.getString(2));
                alumno.setCarrera(cursorAlumnos.getString(3));
                alumno.setAnio_ingreso(cursorAlumnos.getInt(4));


        }
        cursorAlumnos.close();
        return alumno;
    }

    public boolean editarAlumno(int id, String carnet, String nombre, String carrera, int anio_ingreso){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_ALUMNOS + " SET carnet = '"+carnet+"', nombre = '"+nombre+"',carrera = '"+carrera+"',anio_ingreso = '"+anio_ingreso+"' WHERE id ='"+ id +"'" );
            correcto = true;
        } catch (Exception ex){
            Log.i("CATCH ERROR", ex.toString());
            ex.toString();
            correcto = false;
        }finally{
            db.close();
        }
        Log.i("DB RETURN VALUE ", String.valueOf(correcto));
        return correcto;
    }

    public boolean eliminarAlumno(int id){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ALUMNOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex){
            Log.i("CATCH ERROR", ex.toString());
            ex.toString();
            correcto = false;
        }finally{
            db.close();
        }
        Log.i("DB RETURN VALUE ", String.valueOf(correcto));
        return correcto;
    }
}
