package com.example.h071211058_finalmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.db.MovieModel;

import java.util.ArrayList;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder> {
    private ArrayList<MovieModel> items = new ArrayList<>();
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private Context context;
    private LikedAdapter.onSelectData onSelectData;


    public LikedAdapter(Context context) {
        this.context = context;
    }

    public interface onSelectData {
        void onSelected(MovieModel movieModelDB);
    }
    public void setOnSelectData(LikedAdapter.onSelectData onSelectData) {

        this.onSelectData = onSelectData;
    }

    public void setData(ArrayList<MovieModel> item) {
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LikedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liked_movie_layout, parent, false);
        return new LikedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikedAdapter.ViewHolder holder, int position) {
        final MovieModel movieItem = items.get(position);

        Glide.with(context).load(BASE_IMAGE_URL+movieItem.getPosterPath())
                .into(holder.iv_poster);
        holder.tv_title.setText(movieItem.getTitle());
        holder.tv_year.setText(String.valueOf(movieItem.getVoteAverage()));

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(movieItem);
            }

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_year;
        ImageView iv_poster;
        CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}
