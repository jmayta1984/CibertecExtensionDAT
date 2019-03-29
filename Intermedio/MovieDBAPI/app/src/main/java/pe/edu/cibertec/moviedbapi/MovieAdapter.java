package pe.edu.cibertec.moviedbapi;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.LayoutMovie> {

    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public LayoutMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.movie_layout, viewGroup, false);

        LayoutMovie layoutMovie = new LayoutMovie(view);
        return layoutMovie;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutMovie layoutMovie, int position) {

        layoutMovie.tvName.setText(movies.get(position).getTitle());

        layoutMovie.tvDate.setText(movies.get(position).getReleaseDate());

        layoutMovie.tvOverview.setText(movies.get(position).getOverview());


        Glide.with(layoutMovie.itemView).load("http://image.tmdb.org/t/p/w185/"
                + movies.get(position).getPosterPath())
                .into(layoutMovie.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class LayoutMovie extends RecyclerView.ViewHolder {

        TextView tvName;

        TextView tvDate;

        TextView tvOverview;

        ImageView ivMovie;

        public LayoutMovie(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);

            tvOverview = itemView.findViewById(R.id.tvOverview);

            ivMovie = itemView.findViewById(R.id.ivMovie);

        }
    }
}
