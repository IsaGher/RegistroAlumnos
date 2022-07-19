package com.example.registrosalumnos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.registrosalumnos.db.DbAlumnos;
import com.example.registrosalumnos.entidades.Alumnos;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtCarnet, txtCarrera,txtAnioIng;
    FloatingActionButton fabEditar,fabEliminar;
    Button btnGuardar;

    Alumnos alumno;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtCarnet = findViewById(R.id.txtCarnet);
        txtNombre =findViewById(R.id.txtNombre);
        txtCarrera =findViewById(R.id.txtCarrera);
        txtAnioIng=findViewById(R.id.txtAnioIng);
        btnGuardar  = findViewById(R.id.btnGuardar);
        fabEditar  = findViewById(R.id.fabEditar);
        fabEliminar  = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("id");
            }
        }else{
         id = (int) savedInstanceState.getSerializable("id");
        }

        DbAlumnos dbAlumnos = new DbAlumnos(VerActivity.this);
        alumno = dbAlumnos.verAlumno(id);

        if(alumno!=null){
            txtCarnet.setText(alumno.getCarnet());
            txtCarnet.setInputType(InputType.TYPE_NULL);
            txtNombre.setText(alumno.getNombre());
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCarrera.setText(alumno.getCarrera());
            txtCarrera.setInputType(InputType.TYPE_NULL);
            txtAnioIng.setText(String.format("%d", alumno.getAnio_ingreso()));
            txtAnioIng.setInputType(InputType.TYPE_NULL);
            btnGuardar.setVisibility(View.INVISIBLE);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Seguro de eliminar al estudiante?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            if(dbAlumnos.eliminarAlumno(id)){
                            lista();
                            }
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}