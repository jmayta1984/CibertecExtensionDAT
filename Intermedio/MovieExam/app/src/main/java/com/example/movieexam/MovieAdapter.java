package com.example.movieexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviePrototype> {

    private List<Movie> items;


    MovieAdapter() {

    }

    MovieAdapter(List<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MoviePrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prototype_movie, viewGroup, false);


        return new MoviePrototype(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePrototype moviePrototype, int position) {

        moviePrototype.tvName.setText(items.get(position).getTitle());
        moviePrototype.tvCompany.setText(items.get(position).getOverview());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Movie> movies) {
        if (items == null) {
            items = movies;
        } else {
            items.addAll(movies);

        }
        notifyDataSetChanged();
    }

    class MoviePrototype extends RecyclerView.ViewHolder {

        TextView tvName, tvCompany;
        ImageButton btFavorite;

        MoviePrototype(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCompany = itemView.findViewById(R.id.tvOverview);
            btFavorite = itemView.findViewById(R.id.btFavorite);

            btFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppDatabase.getInstance(v.getContext())
                            .movieDao().insert(items.get(getAdapterPosition()));
                    items.remove(items.get(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), items.size());

                }
            });
        }
    }
}
