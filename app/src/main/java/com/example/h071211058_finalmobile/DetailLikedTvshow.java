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
import com.example.h071211058_finalmobile.db.AppDatabase;
import com.example.h071211058_finalmobile.db.MovieModel;
import com.example.h071211058_finalmobile.model.MovieDiscoverResultsItem;
import com.example.h071211058_finalmobile.model.TvshowItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DetailLikedTvshow extends AppCompatActivity {

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    boolean isFavorite = false;
    FloatingActionButton btn_back, btn_like;
    ImageView iv_poster, iv_backdrop;
    TextView tv_title, tv_release, tv_rating, tv_sinopsis;
    MovieModel movieModel;
    TvshowItem tvshowItem;
    private AppDatabase appDatabase;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_liked_tvshow);
        btn_back = findViewById(R.id.btn_back);
        btn_like = findViewById(R.id.btn_like);
        iv_poster = findViewById(R.id.iv_poster);
        iv_backdrop = findViewById(R.id.iv_backdrop);
        tv_title = findViewById(R.id.tv_title);
        tv_release = findViewById(R.id.tv_release);
        tv_rating = findViewById(R.id.tv_rating);
        tv_sinopsis = findViewById(R.id.tv_sinopsis);
        Intent intent = getIntent();
        if (intent.getParcelableExtra("EXTRA_LIKED") != null){
            movieModel = (MovieModel) intent.getParcelableExtra("EXTRA_LIKED");

            if (movieModel != null){
                String title = movieModel.getTitle();
//                LocalDate localDate = LocalDate.parse(movieDiscoverResultsItem.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE);
//                int releaseYear = localDate.getYear();
                double rating = movieModel.getVoteAverage();
                String sinopsis = movieModel.getOverview();
                String poster = movieModel.getPosterPath();
//                String backdrop = movieDiscoverResultsItem.getBackdropPath();

                tv_title.setText(title);
                tv_release.setVisibility(View.GONE);
//                tv_release.setText(String.valueOf(releaseYear));
                tv_sinopsis.setText(sinopsis);
                tv_rating.setText(String.valueOf(rating));


                Glide.with(this).load(BASE_IMAGE_URL + poster)
                        .into(iv_poster);

                iv_backdrop.setVisibility(View.GONE);
//                Glide.with(this).load(BASE_IMAGE_URL + backdrop)
//                        .into(iv_backdrop);

                appDatabase = AppDatabase.initDatabase(getApplicationContext());
                favoriteState();


            }
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
            MovieModel movieModel = new MovieModel();

            movieModel.setId(movieModel.getId());
            movieModel.setTitle(movieModel.getTitle());
            movieModel.setPosterPath(movieModel.getPosterPath());
            movieModel.setOverview(movieModel.getOverview());
            movieModel.setVoteAverage(movieModel.getVoteAverage());
            movieModel.setCategory("tvshow");

            appDatabase.movieDAO().deleteMovie(movieModel);
            Toast.makeText(getApplicationContext(), "DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
            Log.d("DeleteFromFavorite", "Success");

        } catch (Exception e) {
            Log.d("DeleteFromFavorite", "failed delete");
            Toast.makeText(getApplicationContext(), "FAILED DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFavorite() {
        try {
            MovieModel movieModel = new MovieModel();

            movieModel.setId(movieModel.getId());
            movieModel.setTitle(movieModel.getTitle());
            movieModel.setPosterPath(movieModel.getPosterPath());
            movieModel.setOverview(movieModel.getOverview());
            movieModel.setVoteAverage(movieModel.getVoteAverage());
            movieModel.setCategory("tvshow");

            appDatabase.movieDAO().insertMovie(movieModel);

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
        List<MovieModel> getById = appDatabase.movieDAO().getById(movieModel.getId());
        Log.d("FavotiteState", "get a data");
        if (!getById.isEmpty()) {
            isFavorite = true;
        }
    }


}