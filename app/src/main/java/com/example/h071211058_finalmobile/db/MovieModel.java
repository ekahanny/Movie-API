package com.example.h071211058_finalmobile.db;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movie")
public class MovieModel implements Parcelable {
    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeDouble(this.voteAverage);
        dest.writeInt(this.id);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.overview = in.readString();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.voteAverage = in.readDouble();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };


    public static MovieModel fromContentValues(ContentValues values) {
        final MovieModel movie = new MovieModel();
        if (values.containsKey("id")) {
            movie.id = values.getAsInteger("id");
        }
        if (values.containsKey("title")) {
            movie.title = values.getAsString("title");
        }

        if (values.containsKey("poster_path")) {
            movie.posterPath = values.getAsString("poster_path");
        }

        if (values.containsKey("vote_average")) {
            movie.voteAverage = values.getAsDouble("vote_average");
        }
        return movie;
    }
}
