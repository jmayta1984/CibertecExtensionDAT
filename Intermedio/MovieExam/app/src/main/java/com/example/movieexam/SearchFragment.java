package com.example.movieexam;


import android.app.Activity;
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

    TextInputEditText etMovie;
    Button btSearch;
    RecyclerView rvMovies;
    MovieAdapter adapter;
    ProgressBar pbLoad;
    List<Movie> items;

    int page;
    boolean itemsToShow;

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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etMovie = view.findViewById(R.id.etJob);
        btSearch = view.findViewById(R.id.btSearch);
        rvMovies = view.findViewById(R.id.rvMovies);
        pbLoad = view.findViewById(R.id.pbLoad);

        adapter = new MovieAdapter();

        rvMovies.setAdapter(adapter);
        rvMovies.setHasFixedSize(true);

        pbLoad.setVisibility(View.GONE);

        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = rvMovies.getLayoutManager().getChildCount();
                    int totalItemCount = rvMovies.getLayoutManager().getItemCount();

                    int pastVisibleItems = ((LinearLayoutManager) rvMovies.getLayoutManager()).findFirstVisibleItemPosition();

                    Toast.makeText(getContext(), ""
                            + visibleItemCount + "\n"
                            + pastVisibleItems + "\n"
                            + totalItemCount + "\n", Toast.LENGTH_SHORT).show();
                    if (itemsToShow) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                            itemsToShow = false;
                            page += 1;
                            loadItems();
                        }

                    }
                }
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                // Cerrar el KeyBoard
                InputMethodManager imm =
                        (InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // Mostrar el ProgressBar
                pbLoad.setVisibility(View.VISIBLE);
                rvMovies.setVisibility(View.GONE);
                loadItems();
            }
        });

        page = 1;
        itemsToShow = true;

    }

    private void loadItems() {
        String url = "https://api.themoviedb.org/3/search/";
        String apiKey = "3cae426b920b29ed2fb1c0749f258325";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovierInterface movieInterface = retrofit.create(MovierInterface.class);
        Call<MovieResponse> searchMethod = movieInterface.searchMovies(apiKey,
                etMovie.getText().toString(), String.valueOf(page));

        searchMethod.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                pbLoad.setVisibility(View.GONE);
                rvMovies.setVisibility(View.VISIBLE);
                itemsToShow = true;
                if (response.isSuccessful()) {
                    items = response.body().getMovies();
                    adapter.addItems(items);
                    rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}
