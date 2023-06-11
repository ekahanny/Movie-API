package com.example.h071211058_finalmobile.networking;

import com.example.h071211058_finalmobile.model.MovieDiscoverResponse;
import com.example.h071211058_finalmobile.model.TvshowResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieRepository {

        @GET("3/discover/movie?api_key=70f91f08174bbd745a0ec9a420f57534")
        Call<MovieDiscoverResponse> getMovieDiscover();

        @GET("3/discover/tv?api_key=70f91f08174bbd745a0ec9a420f57534")
        Call<TvshowResponse> getTvshow();


}
