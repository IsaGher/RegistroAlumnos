package com.example.registrosalumnos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registrosalumnos.R;
import com.example.registrosalumnos.VerActivity;
import com.example.registrosalumnos.entidades.Alumnos;

import java.util.ArrayList;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnosViewHolder> {

    ArrayList<Alumnos> listaAlumnos;

    public ListaAlumnosAdapter(ArrayList<Alumnos> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_alumno, null, false);
        return new AlumnosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosViewHolder alumnosViewHolder, int i) {
        alumnosViewHolder.viewCarnet.setText(listaAlumnos.get(i).getCarnet());
        alumnosViewHolder.viewNombre.setText(listaAlumnos.get(i).getNombre());
        alumnosViewHolder.viewCarrera.setText(listaAlumnos.get(i).getCarrera());
        alumnosViewHolder.viewAnio.setText(String.valueOf(listaAlumnos.get(i).getAnio_ingreso()));

    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnosViewHolder extends RecyclerView.ViewHolder {

        TextView viewCarnet, viewNombre, viewCarrera, viewAnio;

        public AlumnosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCarnet = itemView.findViewById(R.id.viewCarnet);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCarrera = itemView.findViewById(R.id.viewCarrera);
            viewAnio = itemView.findViewById(R.id.viewAnio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra( "id",listaAlumnos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
