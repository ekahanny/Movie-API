package com.example.h071211058_finalmobile.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211058_finalmobile.R;
import com.example.h071211058_finalmobile.db.AppDatabase;
import com.example.h071211058_finalmobile.db.MovieModel;
import com.example.h071211058_finalmobile.db.MovieModel;
import com.example.h071211058_finalmobile.model.MovieDiscoverResultsItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetailMovie extends AppCompatActivity {
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    boolean isFavorite = false;
    FloatingActionButton btn_back, btn_like;
    ImageView iv_poster, iv_backdrop;
    TextView tv_title, tv_release, tv_rating, tv_sinopsis;
    MovieDiscoverResultsItem movieDiscoverResultsItem;
    MovieModel movieModel;
    private AppDatabase appDatabase;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        btn_back = findViewById(R.id.btn_back);
        btn_like = findViewById(R.id.btn_like);
        iv_poster = findViewById(R.id.iv_poster);
        iv_backdrop = findViewById(R.id.iv_backdrop);
        tv_title = findViewById(R.id.tv_title);
        tv_release = findViewById(R.id.tv_release);
        tv_rating = findViewById(R.id.tv_rating);
        tv_sinopsis = findViewById(R.id.tv_sinopsis);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("EXTRA_MOVIE") != null){

            movieDiscoverResultsItem = (MovieDiscoverResultsItem) intent.getParcelableExtra("EXTRA_MOVIE");

            if (movieDiscoverResultsItem != null){
                String title = movieDiscoverResultsItem.getTitle();

                LocalDate localDate = LocalDate.parse(movieDiscoverResultsItem.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                int releaseYear = localDate.getYear();
                double rating = movieDiscoverResultsItem.getVoteAverage();
                String sinopsis = movieDiscoverResultsItem.getOverview();
                String poster = movieDiscoverResultsItem.getPosterPath();
                String backdrop = movieDiscoverResultsItem.getBackdropPath();

                tv_title.setText(title);
                tv_release.setText(String.valueOf(releaseYear));
                tv_sinopsis.setText(sinopsis);
                tv_rating.setText(String.valueOf(rating));


                Glide.with(this).load(BASE_IMAGE_URL + poster)
                        .into(iv_poster);

                Glide.with(this).load(BASE_IMAGE_URL + backdrop)
                        .into(iv_backdrop);

                appDatabase = AppDatabase.initDatabase(getApplicationContext());
                favoriteState();
            }


            btn_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFavorite) {
                        deleteFavorite();
                        isFavorite = false;
                        setFavorite();
                    } else {
                        saveToFavorite();
                        isFavorite = true;
                        setFavorite();
                    }
                }
            });
        }


        // Mengatur aksi untuk FloatingButton btn_back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        favoriteState();
        setFavorite();
    }

    private void deleteFavorite() {
        try {
            MovieModel movieModelDb = new MovieModel();

            movieModelDb.setId(movieDiscoverResultsItem.getId());
            movieModelDb.setTitle(movieDiscoverResultsItem.getTitle());
            movieModelDb.setPosterPath(movieDiscoverResultsItem.getPosterPath());
            movieModelDb.setOverview(movieDiscoverResultsItem.getOverview());
            movieModelDb.setVoteAverage(movieDiscoverResultsItem.getVoteAverage());
            movieModelDb.setCategory("movie");

            appDatabase.movieDAO().deleteMovie(movieModelDb);
            Toast.makeText(getApplicationContext(), "DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
            Log.d("DeleteFromFavorite", "Success");

        } catch (Exception e) {
            Log.d("DeleteFromFavorite", "failed delete");
            Toast.makeText(getApplicationContext(), "FAILED DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFavorite() {
        try {
            MovieModel movieModelDb = new MovieModel();

            movieModelDb.setId(movieDiscoverResultsItem.getId());
            movieModelDb.setTitle(movieDiscoverResultsItem.getTitle());
            movieModelDb.setPosterPath(movieDiscoverResultsItem.getPosterPath());
            movieModelDb.setOverview(movieDiscoverResultsItem.getOverview());
            movieModelDb.setVoteAverage(movieDiscoverResultsItem.getVoteAverage());
            movieModelDb.setCategory("movie");

            appDatabase.movieDAO().insertMovie(movieModelDb);

            Toast.makeText(getApplicationContext(), "SAVE TO FAVORITE", Toast.LENGTH_SHORT).show();
            Log.d("SaveFromFavorite", "Success");

        } catch (Exception e) {
            Log.d("SaveFromFavorite", "failed save");
            Toast.makeText(getApplicationContext(), "FAILED SAVE", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFavorite() {
        if (isFavorite) {
            btn_like.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            btn_like.setImageResource(R.drawable.baseline_favorite_border_24);
        }
    }

    public void favoriteState() {
        List<MovieModel> getById = appDatabase.movieDAO().getById(movieDiscoverResultsItem.getId());
        Log.d("FavotiteState", "get a data");
        if (!getById.isEmpty()) {
            isFavorite = true;
        }
    }
}