package com.example.maximgerasimenko.observer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie(){}

    //Metoda dla sortowanie wedlug alfabetu.
    public static final Comparator<Movie> BY_NAME_ALPHABETICAL = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            return movie.originalTitle.compareTo(t1.originalTitle);
        }
    };

    //Pobieranie posteru
    public String getPosterPath(){
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }
    //Ustawienie posteru.
    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }
    //Pobieranie "Overview"
    public String getOverview(){
        return overview;
    }
    //Ustawienie "Overview"
    public void setOverview(String overview){
        this.overview = overview;
    }
    //Pobieranie daty
    public String getReleaseDate(){
        return releaseDate;
    }
    //Pobieranie ID filmu
    public Integer getId(){
        return id;
    }
    //Ustawienie ID filmu
    public void setId(Integer id){
        this.id = id;
    }
    //Pobieranie oryginalnej nazwy
    public String getOriginalTitle(){
        return originalTitle;
    }
    //Ustawienie oryginalnej nazwy
    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }
    //Pobieranie nazwy
    public String getTitle(){
        return title;
    }
    //Ustawienie nazwy
    public void setTitle(String title){
        this.title = title;
    }
    //Pobieranie "VoteAverage"
    public Double getVoteAverage(){return voteAverage; }
    //Ustawienie "VoteAverage"
    public void setVoteAverage(Double voteAvarage){
        this.voteAverage = voteAvarage;
    }
}
















