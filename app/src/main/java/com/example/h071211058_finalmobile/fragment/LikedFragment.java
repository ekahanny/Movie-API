package com.example.h071211058_finalmobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h071211058_finalmobile.DetailLikedMovie;
import com.example.h071211058_finalmobile.DetailLikedTvshow;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.adapter.LikedAdapter;
import com.example.h071211058_finalmobile.adapter.LikedTvshowAdapter;
import com.example.h071211058_finalmobile.db.AppDatabase;
import com.example.h071211058_finalmobile.db.MovieModel;

import java.util.ArrayList;

public class LikedFragment extends Fragment {
    private ArrayList<MovieModel> listLiked = new ArrayList<>();
    private ArrayList<MovieModel> tvShowLiked = new ArrayList<>();


    private AppDatabase appDatabase;
    private LikedAdapter likedAdapter;
    private LikedTvshowAdapter likedTvshowAdapter;
    RecyclerView rv_liked;

    public LikedFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_liked = view.findViewById(R.id.rv_liked);

        rv_liked.setLayoutManager(new LinearLayoutManager(getContext()));

        if (this.appDatabase == null) {
            appDatabase = AppDatabase.initDatabase(getContext());
        }

        listLiked.addAll(appDatabase.movieDAO().getByCategory("movie"));
        tvShowLiked.addAll(appDatabase.movieDAO().getByCategory("tvshow"));

        likedAdapter = new LikedAdapter(getContext());
        likedTvshowAdapter = new LikedTvshowAdapter(getContext());
        likedAdapter.setOnSelectData(new LikedAdapter.onSelectData() {
            @Override
            public void onSelected(MovieModel movieModelDB) {
                Intent intent = new Intent(getActivity(), DetailLikedMovie.class);
                intent.putExtra("EXTRA_LIKED", movieModelDB);
                startActivity(intent);
            }
        });
        likedTvshowAdapter.setOnSelectData(new LikedTvshowAdapter.onSelectData() {
            @Override
            public void onSelected(MovieModel movieModelDB) {
                Intent intent = new Intent(getActivity(), DetailLikedTvshow.class);
                intent.putExtra("EXTRA_LIKED", movieModelDB);
                startActivity(intent);
            }
        });

        likedAdapter.notifyDataSetChanged();
        likedAdapter.setData(listLiked);

        likedTvshowAdapter.notifyDataSetChanged();
        likedTvshowAdapter.setData(tvShowLiked);

        rv_liked.setAdapter(likedAdapter);
        rv_liked.setAdapter(likedTvshowAdapter);

    }

}