package com.example.h071211058_finalmobile.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.model.TvshowItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetailTvShow extends AppCompatActivity {
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    FloatingActionButton btn_back, btn_like;
    ImageView iv_poster, iv_backdrop;
    TextView tv_title, tv_release, tv_rating, tv_sinopsis;
    TvshowItem tvshowItem;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        btn_back = findViewById(R.id.btn_back);
        btn_like = findViewById(R.id.btn_like);
        iv_poster = findViewById(R.id.iv_poster);
        iv_backdrop = findViewById(R.id.iv_backdrop);
        tv_title = findViewById(R.id.tv_title);
        tv_release = findViewById(R.id.tv_release);
        tv_rating = findViewById(R.id.tv_rating);
        tv_sinopsis = findViewById(R.id.tv_sinopsis);

        tvshowItem = (TvshowItem) getIntent().getParcelableExtra("EXTRA_TVSHOW");
        if (tvshowItem != null){
            String title = tvshowItem.getOriginalName();

            LocalDate localDate = LocalDate.parse(tvshowItem.getFirstAirDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            int releaseYear = localDate.getYear();

            double rating = tvshowItem.getVoteAverage();

            String sinopsis = tvshowItem.getOverview();
            if (sinopsis.length() > 0){
                tv_sinopsis.setText(sinopsis);
            }else{
                tv_sinopsis.setText("-");
            }

            String poster = tvshowItem.getPosterPath();
            String backdrop = tvshowItem.getBackdropPath();

            tv_title.setText(title);
            tv_release.setText(String.valueOf(releaseYear));

            tv_rating.setText(String.valueOf(rating));


            Glide.with(this).load(BASE_IMAGE_URL + poster)
                    .into(iv_poster);

            Glide.with(this).load(BASE_IMAGE_URL + backdrop)
                    .into(iv_backdrop);

        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}