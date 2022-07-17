package com.example.registrosalumnos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registrosalumnos.db.DbAlumnos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtCarnet, txtNombre, txtCarrera, txtAnioIng;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtCarnet = findViewById(R.id.txtCarnet);
        txtNombre = findViewById(R.id.txtNombre);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtAnioIng = findViewById(R.id.txtAnioIng);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAlumnos dbAlumnos = new DbAlumnos(NuevoActivity.this);
                long id = dbAlumnos.insertarAlumno(txtCarnet.getText().toString(), txtNombre.getText().toString(), txtCarrera.getText().toString(), Integer.parseInt(txtAnioIng.getText().toString()));

                if(id>0){
                    Toast.makeText(NuevoActivity.this, "Datos Guardados Correctamente", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevoActivity.this, "Error al guardar", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void limpiar(){
        txtCarnet.setText("");
        txtNombre.setText("");
        txtCarrera.setText("");
        txtAnioIng.setText("");
    }
}