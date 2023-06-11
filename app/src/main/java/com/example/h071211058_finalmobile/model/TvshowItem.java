package com.example.h071211058_finalmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvshowItem implements Parcelable {
    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("genre_ids")
    private ArrayList<Integer> genreIds;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("origin_country")
    private ArrayList<String> originCountry;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    protected TvshowItem(Parcel in) {
        firstAirDate = in.readString();
        overview = in.readString();
        originalLanguage = in.readString();
        posterPath = in.readString();
        originCountry = in.createStringArrayList();
        backdropPath = in.readString();
        originalName = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readDouble();
        name = in.readString();
        id = in.readInt();
        voteCount = in.readInt();
    }

    public static final Creator<TvshowItem> CREATOR = new Creator<TvshowItem>() {
        @Override
        public TvshowItem createFromParcel(Parcel in) {
            return new TvshowItem(in);
        }

        @Override
        public TvshowItem[] newArray(int size) {
            return new TvshowItem[size];
        }
    };

    public void setFirstAirDate(String firstAirDate){
        this.firstAirDate = firstAirDate;
    }

    public String getFirstAirDate(){
        return firstAirDate;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getOverview(){
        return overview;
    }

    public void setOriginalLanguage(String originalLanguage){
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage(){
        return originalLanguage;
    }

    public void setGenreIds(ArrayList<Integer> genreIds){
        this.genreIds = genreIds;
    }

    public ArrayList<Integer> getGenreIds(){
        return genreIds;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public void setOriginCountry(ArrayList<String> originCountry){
        this.originCountry = originCountry;
    }

    public ArrayList<String> getOriginCountry(){
        return originCountry;
    }

    public void setBackdropPath(String backdropPath){
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath(){
        return backdropPath;
    }

    public void setOriginalName(String originalName){
        this.originalName = originalName;
    }

    public String getOriginalName(){
        return originalName;
    }

    public void setPopularity(double popularity){
        this.popularity = popularity;
    }

    public double getPopularity(){
        return popularity;
    }

    public void setVoteAverage(double voteAverage){
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage(){
        return voteAverage;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setVoteCount(int voteCount){
        this.voteCount = voteCount;
    }

    public int getVoteCount(){
        return voteCount;
    }



    @Override
    public String toString(){
        return
                "TvshowItem{" +
                        "first_air_date = '" + firstAirDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",genre_ids = '" + genreIds + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",origin_country = '" + originCountry + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",original_name = '" + originalName + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeString(posterPath);
        parcel.writeStringList(originCountry);
        parcel.writeString(backdropPath);
        parcel.writeString(originalName);
        parcel.writeDouble(popularity);
        parcel.writeDouble(voteAverage);
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(voteCount);
    }
}
