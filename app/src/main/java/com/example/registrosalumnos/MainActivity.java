package com.example.registrosalumnos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.registrosalumnos.adaptadores.ListaAlumnosAdapter;
import com.example.registrosalumnos.db.DbAlumnos;
import com.example.registrosalumnos.entidades.Alumnos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaAlumnos;
    ArrayList<Alumnos> listaArrayAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaAlumnos = findViewById(R.id.listaAlumnos);
        listaAlumnos.setLayoutManager(new LinearLayoutManager(this));

        DbAlumnos dbAlumnos = new DbAlumnos(MainActivity.this);

        listaArrayAlumnos = new ArrayList<>();

        ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(dbAlumnos.mostrarAlumnos());
        listaAlumnos.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}