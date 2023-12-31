package com.example.h071211058_finalmobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211058_finalmobile.DetailLikedMovie;
import com.example.h071211058_finalmobile.activity.DetailMovie;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.adapter.MovieAdapter;
import com.example.h071211058_finalmobile.model.MovieDiscoverResultsItem;
import com.example.h071211058_finalmobile.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    RecyclerView rv_movies;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    ProgressBar progressBar;
    TextView tv_internet;
    private Handler handler;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        tv_internet = view.findViewById(R.id.tv_internet);

        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setOnSelectData(new MovieAdapter.onSelectData() {
            @Override
            public void onSelected(MovieDiscoverResultsItem movieDiscoverResultsItem) {
                Intent intent1 = new Intent(getActivity(), DetailMovie.class);
                intent1.putExtra("EXTRA_MOVIE", movieDiscoverResultsItem);
                startActivity(intent1);
            }
        });
        movieAdapter.notifyDataSetChanged();

        rv_movies = view.findViewById(R.id.rv_movies);
        rv_movies.setLayoutManager(new GridLayoutManager(getContext(), 2));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setMovieDiscover();
        movieViewModel.getMoviesDiscover().observe(getViewLifecycleOwner(), getMovieDiscover);

        rv_movies.setAdapter(movieAdapter);
    }

    private Observer<ArrayList<MovieDiscoverResultsItem>> getMovieDiscover = new Observer<ArrayList<MovieDiscoverResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieDiscoverResultsItem> movieDiscoverResultsItems) {
            if (movieDiscoverResultsItems != null) {
                movieAdapter.setData(movieDiscoverResultsItems);
            }
        }
    };
}
