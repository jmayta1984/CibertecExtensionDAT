package com.example.jobs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoritePrototype> {

    List<Job> items;
    @NonNull
    @Override
    public FavoritePrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prototype_favorite,viewGroup,false);

        return new FavoritePrototype(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePrototype favoritePrototype, final int position) {
        favoritePrototype.tvCompany.setText(items.get(position).getCompany());
        favoritePrototype.tvTitle.setText(items.get(position).getTitle());

        favoritePrototype.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job = new Job();

                job.setId(items.get(position).getId());

                AppDatabase.getInstance(v.getContext()).jobDao().delete(job);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class FavoritePrototype extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCompany;

        ImageButton btDelete;

        public FavoritePrototype(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvName);
            tvCompany = itemView.findViewById(R.id.tvCompany);

            btDelete = itemView.findViewById(R.id.btDelete);

        }
    }
}
