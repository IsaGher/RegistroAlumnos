package com.example.registrosalumnos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registrosalumnos.db.DbAlumnos;
import com.example.registrosalumnos.entidades.Alumnos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtCarnet, txtCarrera,txtAnioIng;
    FloatingActionButton fabEditar,fabEliminar;
    Button btnGuardar;
boolean correcto = false;
    Alumnos alumno;
    int id = 0;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtCarnet = findViewById(R.id.txtCarnet);
        txtNombre =findViewById(R.id.txtNombre);
        txtCarrera =findViewById(R.id.txtCarrera);
        txtAnioIng=findViewById(R.id.txtAnioIng);
        btnGuardar  = findViewById(R.id.btnGuardar);
//
        fabEditar  = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar  = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);
        //
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

        DbAlumnos dbAlumnos = new DbAlumnos(EditarActivity.this);
        alumno = dbAlumnos.verAlumno(id);

        if(alumno!=null){
            txtCarnet.setText(alumno.getCarnet());
            //txtCarnet.setInputType(InputType.TYPE_NULL);
            txtNombre.setText(alumno.getNombre());
           // txtNombre.setInputType(InputType.TYPE_NULL);
            txtCarrera.setText(alumno.getCarrera());
           // txtCarrera.setInputType(InputType.TYPE_NULL);
            txtAnioIng.setText(String.format("%d", alumno.getAnio_ingreso()));
            //txtAnioIng.setInputType(InputType.TYPE_NULL);
           // btnGuardar.setVisibility(View.INVISIBLE);
        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtCarnet.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtCarrera.getText().toString().equals("") && !txtAnioIng.getText().toString().equals("")  ){

                   correcto = dbAlumnos.editarAlumno(id,txtCarnet.getText().toString(),txtNombre.getText().toString(),txtCarrera.getText().toString(),Integer.parseInt(txtAnioIng.getText().toString()));
                    Log.i("CLICK EVENT ", String.valueOf(correcto));
                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private  void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
}
