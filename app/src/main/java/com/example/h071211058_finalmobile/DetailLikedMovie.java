package com.example.h071211058_finalmobile;

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
import com.example.h071211058_finalmobile.activity.DetailMovie;
import com.example.h071211058_finalmobile.activity.MainActivity;
import com.example.h071211058_finalmobile.db.AppDatabase;
import com.example.h071211058_finalmobile.db.MovieModel;
import com.example.h071211058_finalmobile.model.MovieDiscoverResultsItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetailLikedMovie extends AppCompatActivity {
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    boolean isFavorite = false;
    FloatingActionButton btn_back, btn_like;
    ImageView iv_poster, iv_backdrop;
    TextView tv_title, tv_release, tv_rating, tv_sinopsis;
    MovieModel MovieModel;
    MovieDiscoverResultsItem movieDiscoverResultsItem;

    private AppDatabase appDatabase;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_liked_movie);
        btn_back = findViewById(R.id.btn_back);
        btn_like = findViewById(R.id.btn_like);
        iv_poster = findViewById(R.id.iv_poster);
        iv_backdrop = findViewById(R.id.iv_backdrop);
        tv_title = findViewById(R.id.tv_title);
        tv_release = findViewById(R.id.tv_release);
        tv_rating = findViewById(R.id.tv_rating);
        tv_sinopsis = findViewById(R.id.tv_sinopsis);

        MovieModel = (MovieModel) getIntent().getParcelableExtra("EXTRA_LIKED");

        String title = MovieModel.getTitle();

//        LocalDate localDate = LocalDate.parse(MovieModel.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE);
//        int releaseYear = localDate.getYear();
        double rating = MovieModel.getVoteAverage();
        String sinopsis = MovieModel.getOverview();
        String poster = MovieModel.getPosterPath();
//        String backdrop = MovieModel.getBackdropPath();

        tv_title.setText(title);
//        tv_release.setText(String.valueOf(releaseYear));
        tv_sinopsis.setText(sinopsis);
        tv_rating.setText(String.valueOf(rating));


        Glide.with(this).load(BASE_IMAGE_URL + poster)
                .into(iv_poster);

//            Glide.with(this).load(BASE_IMAGE_URL + backdrop)
//                    .into(iv_backdrop);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());
        favoriteState();

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

        // Mengatur aksi untuk FloatingButton btn_back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailLikedMovie.this, MainActivity.class);
                startActivity(intent);
            }
        });

        favoriteState();
        setFavorite();
    }

    private void deleteFavorite() {
        try {
            MovieModel MovieModel = new MovieModel();

            MovieModel.setId(MovieModel.getId());
            MovieModel.setTitle(MovieModel.getTitle());
            MovieModel.setPosterPath(MovieModel.getPosterPath());
            MovieModel.setOverview(MovieModel.getOverview());
            MovieModel.setVoteAverage(MovieModel.getVoteAverage());
            MovieModel.setCategory("movie");

            appDatabase.movieDAO().deleteMovie(MovieModel);
            Toast.makeText(getApplicationContext(), "DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
            Log.d("DeleteFromFavorite", "Success");

        } catch (Exception e) {
            Log.d("DeleteFromFavorite", "failed delete");
            Toast.makeText(getApplicationContext(), "FAILED DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFavorite() {
        try {
            MovieModel MovieModel = new MovieModel();

            MovieModel.setId(MovieModel.getId());
            MovieModel.setTitle(MovieModel.getTitle());
            MovieModel.setPosterPath(MovieModel.getPosterPath());
            MovieModel.setOverview(MovieModel.getOverview());
            MovieModel.setVoteAverage(MovieModel.getVoteAverage());
            MovieModel.setCategory("movie");

            appDatabase.movieDAO().insertMovie(MovieModel);

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
        List<MovieModel> getById = appDatabase.movieDAO().getById(MovieModel.getId());
        Log.d("FavotiteState", "get a data");
        if (!getById.isEmpty()) {
            isFavorite = true;
        }
    }


}