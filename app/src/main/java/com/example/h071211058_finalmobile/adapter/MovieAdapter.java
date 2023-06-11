package com.example.h071211058_finalmobile.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.model.MovieDiscoverResultsItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieDiscoverResultsItem> movieDiscoverItems = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    private MovieAdapter.onSelectData onSelectData;


    public interface onSelectData {
        void onSelected(MovieDiscoverResultsItem movieDiscoverResultsItem);
    }

    public MovieAdapter(Context context) {

        this.context = context;
    }

    public void setOnSelectData(onSelectData onSelectData) {

        this.onSelectData = onSelectData;
    }


    public void setData(ArrayList<MovieDiscoverResultsItem> items){
        movieDiscoverItems.clear();
        movieDiscoverItems.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_layout, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        MovieDiscoverResultsItem movieDiscoverResultsItem = movieDiscoverItems.get(position);

        Glide.with(context).load(BASE_IMAGE_URL+movieDiscoverItems.get(position)
                        .getPosterPath())
                .into(holder.iv_poster);

        holder.tv_title.setText(movieDiscoverItems.get(position).getTitle());

        LocalDate localDate = LocalDate.parse(movieDiscoverItems.get(position).getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        int year = localDate.getYear();
        holder.tv_year.setText(String.valueOf(year));

        holder.cv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(movieDiscoverResultsItem);
            }
        });
    }

    @Override
    public int getItemCount() {

        return movieDiscoverItems.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cv_movie;
        public ImageView iv_poster;
        public TextView tv_title;
        public TextView tv_year;

        public ViewHolder(View itemView) {
            super(itemView);
            cv_movie = itemView.findViewById(R.id.cv_movie);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);
        }
    }
}
