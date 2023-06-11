package com.example.h071211058_finalmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvshowResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<TvshowItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setPage(int page){
        this.page = page;
    }

    public int getPage(){
        return page;
    }

    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }

    public int getTotalPages(){
        return totalPages;
    }

    public void setResults(ArrayList<TvshowItem> results){
        this.results = results;
    }

    public ArrayList<TvshowItem> getResults(){
        return results;
    }

    public void setTotalResults(int totalResults){
        this.totalResults = totalResults;
    }

    public int getTotalResults(){
        return totalResults;
    }

    @Override
    public String toString(){
        return
                "TvshowResponse{" +
                        "page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + totalResults + '\'' +
                        "}";
    }
}

