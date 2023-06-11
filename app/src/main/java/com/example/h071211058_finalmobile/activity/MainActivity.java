package com.example.h071211058_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.fragment.LikedFragment;
import com.example.h071211058_finalmobile.fragment.MovieFragment;
import com.example.h071211058_finalmobile.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    MovieFragment movieFragment = new MovieFragment();
    TvShowFragment tvShowFragment = new TvShowFragment();
    LikedFragment likedFragment = new LikedFragment();
    TextView tv_header;
    ImageView iv_movie, iv_tvshow, iv_liked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_header = findViewById(R.id.tv_header);
        iv_movie = findViewById(R.id.iv_movie);
        iv_tvshow = findViewById(R.id.iv_tvshow);
        iv_liked = findViewById(R.id.iv_liked);




        Fragment fragment = getSupportFragmentManager().findFragmentByTag(MovieFragment.class.getSimpleName());
        if (!(fragment instanceof MovieFragment)){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, movieFragment, MovieFragment.class.getSimpleName())
                    .commit();
        }

        iv_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, movieFragment)
                        .commit();
                tv_header.setText("Movie");
            }
        });

        iv_tvshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, tvShowFragment)
                        .commit();
                tv_header.setText("TV Show");
            }
        });

        iv_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, likedFragment)
                        .commit();
                tv_header.setText("Favorite");
            }
        });
    }
}