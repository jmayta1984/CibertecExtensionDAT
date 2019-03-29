package pe.cibertec.movierxjava.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.cibertec.movierxjava.R;
import pe.cibertec.movierxjava.models.Pelicula;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.CeldaPelicula> {


    private ArrayList<Pelicula> peliculas;

    public PeliculaAdapter(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public CeldaPelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.celda_pelicula,parent,false);
        return new CeldaPelicula(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CeldaPelicula celda, int posicion) {
        celda.tvTitulo.setText(peliculas.get(posicion).getTitulo());
        celda.tvDescripcion.setText(peliculas.get(posicion).getDescripcion());
        Glide.with(celda.itemView)
                .load("https://image.tmdb.org/t/p/w500/" + peliculas.get(posicion).getUrlImagen())
                .into(celda.ivPoster);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class CeldaPelicula extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;

        @BindView(R.id.tvDescripcion)
        TextView tvDescripcion;

        @BindView(R.id.ivPoster)
        ImageView ivPoster;

        public CeldaPelicula(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
