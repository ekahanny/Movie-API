package com.example.h071211058_finalmobile.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.model.TvshowItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.ViewHolder> {

    private ArrayList<TvshowItem> tvshowItems = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    private TvshowAdapter.onSelectData onSelectData;


    public interface onSelectData {
        void onSelected(TvshowItem tvshowItem);
    }


    public TvshowAdapter(Context context) {
        this.context = context;
    }

    public void setOnSelectData(TvshowAdapter.onSelectData onSelectData) {
        this.onSelectData = onSelectData;
    }

    public void setData(ArrayList<TvshowItem> items){
        tvshowItems.clear();
        tvshowItems.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TvshowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_layout, parent, false);
        return new TvshowAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TvshowAdapter.ViewHolder holder, int position) {
        TvshowItem tvshowItem = tvshowItems.get(position);

        Glide.with(context).load(BASE_IMAGE_URL+tvshowItems.get(position)
                        .getPosterPath())
                .into(holder.iv_poster);

        holder.tv_title.setText(tvshowItems.get(position).getName());

        LocalDate localDate = LocalDate.parse(tvshowItems.get(position).getFirstAirDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        int year = localDate.getYear();
        holder.tv_year.setText(String.valueOf(year));

        holder.cv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(tvshowItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvshowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cv_movie;
        public ImageView iv_poster;
        public TextView tv_title;
        public TextView tv_year;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_movie = itemView.findViewById(R.id.cv_movie);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);

        }
    }
}

