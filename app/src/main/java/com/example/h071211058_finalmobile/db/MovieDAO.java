package com.example.h071211058_finalmobile.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert
    void insertMovie(MovieModel movieModelDb);

    @Query("SELECT * FROM favorite_movie")
    List<MovieModel> getMovieDb();

    @Delete
    void deleteMovie(MovieModel movieModelDb);

    @Query("SELECT * FROM favorite_movie WHERE id = :idMovie LIMIT 1")
    List<MovieModel> getById(int idMovie);

    @Query("SELECT * FROM favorite_movie WHERE category = :category")
    List<MovieModel> getByCategory(String category);

    //query bawah ini untuk yg contentprovider

    @Query("SELECT COUNT(*) FROM " + "favorite_movie")
    int count();

    @Insert
    long[] insertAll(MovieModel[] movie);

    @Insert
    long insert(MovieModel movieModelDb);

    @Query("SELECT * FROM favorite_movie")
    Cursor getAllMovie();

    @Query("SELECT * FROM favorite_movie WHERE id = :idMovie LIMIT 1")
    Cursor getMovieById(long idMovie);


}