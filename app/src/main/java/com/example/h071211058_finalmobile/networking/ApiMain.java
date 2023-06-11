package com.example.h071211058_finalmobile.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {

    private Retrofit retrofit;

    public MovieRepository getApiMovie(){
        String BASE_URL = "https://api.themoviedb.org/";
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieRepository.class);
    }
}
