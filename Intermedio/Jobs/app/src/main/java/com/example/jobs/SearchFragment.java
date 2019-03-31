package com.example.jobs;


import android.app.Activity;
import android.arch.persistence.room.Database;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    TextInputEditText etJob;
    Button btSearch;
    RecyclerView rvJobs;
    JobAdapter adapter;
    ProgressBar pbLoad;
    List<Job> items;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etJob = view.findViewById(R.id.etJob);
        btSearch = view.findViewById(R.id.btSearch);
        rvJobs = view.findViewById(R.id.rvJobs);
        pbLoad = view.findViewById(R.id.pbLoad);

        pbLoad.setVisibility(View.GONE);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                // Cerrar el KeyBoard
                InputMethodManager imm =
                        (InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                // Mostrar el ProgressBar
                pbLoad.setVisibility(View.VISIBLE);

                String url = "https://jobs.github.com/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JobInterface jobInterface = retrofit.create(JobInterface.class);

                Call<List<Job>> searchMethod = jobInterface.searchJobs(etJob.getText().toString());
                searchMethod.enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        pbLoad.setVisibility(View.GONE);

                        if (response.isSuccessful()) {

                            items = response.body();
                            adapter = new JobAdapter(items);

                            rvJobs.setAdapter(adapter);
                            rvJobs.setLayoutManager(new LinearLayoutManager(v.getContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        pbLoad.setVisibility(View.GONE);
                    }
                });
            }
        });


    }
}
