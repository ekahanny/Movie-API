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
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.activity.DetailTvShow;
import com.example.h071211058_finalmobile.adapter.TvshowAdapter;
import com.example.h071211058_finalmobile.model.TvshowItem;
import com.example.h071211058_finalmobile.viewmodel.TvshowViewModel;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {
    RecyclerView rv_tv_show;
    private TvshowAdapter tvshowAdapter;
    private TvshowViewModel tvshowViewModel;
    ProgressBar progressBar;
    TextView tv_internet;
    private Handler handler;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        tv_internet = view.findViewById(R.id.tv_internet);

        tvshowAdapter = new TvshowAdapter(getContext());
        tvshowAdapter.setOnSelectData(new TvshowAdapter.onSelectData() {
            @Override
            public void onSelected(TvshowItem tvshowItem) {
                Intent intent = new Intent(getActivity(), DetailTvShow.class);
                intent.putExtra("EXTRA_TVSHOW", tvshowItem);
                startActivity(intent);
            }
        });
        tvshowAdapter.notifyDataSetChanged();

        rv_tv_show = view.findViewById(R.id.rv_tv_show);
        rv_tv_show.setLayoutManager(new GridLayoutManager(getContext(), 2));

        handler = new Handler(Looper.getMainLooper());

        tvshowViewModel = new ViewModelProvider(this).get(TvshowViewModel.class);
        tvshowViewModel.setTvshowDiscover();
        tvshowViewModel.getTvshow().observe(getViewLifecycleOwner(), getTvshow);

        rv_tv_show.setAdapter(tvshowAdapter);
    }

    private Observer<ArrayList<TvshowItem>> getTvshow = new Observer<ArrayList<TvshowItem>>() {
        @Override
        public void onChanged(ArrayList<TvshowItem> tvshowItems) {
            if (tvshowItems != null){
                tvshowAdapter.setData(tvshowItems);
            }
        }
    };
}