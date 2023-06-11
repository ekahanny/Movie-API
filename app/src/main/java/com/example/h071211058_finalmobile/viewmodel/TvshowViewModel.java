package com.example.h071211058_finalmobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.h071211058_finalmobile.model.TvshowItem;
import com.example.h071211058_finalmobile.model.TvshowResponse;
import com.example.h071211058_finalmobile.networking.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvshowViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<TvshowItem>> listTvshow = new MutableLiveData<>();

    public void setTvshowDiscover(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiMovie().getTvshow().enqueue(new Callback<TvshowResponse>() {
            @Override
            public void onResponse(Call<TvshowResponse> call, Response<TvshowResponse> response) {
                TvshowResponse responseDiscover = response.body();
                if (responseDiscover != null && responseDiscover.getResults() != null){
                    ArrayList<TvshowItem> tvshowItems = responseDiscover.getResults();
                    listTvshow.postValue(tvshowItems);

                }
            }

            @Override
            public void onFailure(Call<TvshowResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<TvshowItem>> getTvshow(){
        return listTvshow;
    }
}

