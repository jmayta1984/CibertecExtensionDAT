package com.example.jobs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobPrototype> {

    List<Job> items;

    public JobAdapter(List<Job> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public JobPrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prototype_job,viewGroup,false);


        return new JobPrototype(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobPrototype jobPrototype, final int position) {

        jobPrototype.tvName.setText(items.get(position).getTitle());
        jobPrototype.tvCompany.setText(items.get(position).getCompany());

        jobPrototype.btFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job = new Job();
                job.setTitle(items.get(position).getTitle());
                job.setCompany(items.get(position).getCompany());

                AppDatabase.getInstance(v.getContext()).jobDao().insert(job);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class JobPrototype extends RecyclerView.ViewHolder {

        TextView tvName, tvCompany;
        ImageButton btFavorite;

        public JobPrototype(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvCompany = itemView.findViewById(R.id.tvCompany);

            btFavorite = itemView.findViewById(R.id.btFavorite);
        }
    }
}
