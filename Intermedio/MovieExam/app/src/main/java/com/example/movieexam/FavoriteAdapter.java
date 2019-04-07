package com.example.movieexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoritePrototype> {

    private List<Movie> items;

    FavoriteAdapter(List<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FavoritePrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prototype_favorite, viewGroup, false);

        return new FavoritePrototype(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritePrototype favoritePrototype, int position) {
        favoritePrototype.tvOverview.setText(items.get(position).getOverview());
        favoritePrototype.tvTitle.setText(items.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FavoritePrototype extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview;

        ImageButton btDelete;

        FavoritePrototype(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvName);
            tvOverview = itemView.findViewById(R.id.tvOverview);

            btDelete = itemView.findViewById(R.id.btDelete);

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppDatabase.getInstance(v.getContext()).movieDao()
                            .delete(items.get(getAdapterPosition()));
                    items.remove(items.get(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), items.size());


                }
            });

        }
    }
}
