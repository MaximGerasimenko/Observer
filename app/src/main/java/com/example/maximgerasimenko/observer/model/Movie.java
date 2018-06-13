package com.example.maximgerasimenko.observer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("ganre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("populatiry")
    private Double populatiry;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_avatage")
    private Double voteAvarage;

    public Movie(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, Integer id, String originalTitle, String originalLanguage, String title, String backdropPath, double populatiry, Integer voteCount, Boolean video, Double voteAvarage){
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.populatiry = populatiry;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAvarage = voteAvarage;
    }
    String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public String getPosterPath(){
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public boolean isAdult(){
        return adult;
    }
    public void setAdult(boolean adult){
        this.adult = adult;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds(){
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds){
       this.genreIds = genreIds;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBackdropPath(){
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath){
        this.backdropPath = backdropPath;
    }

    public Double getPopulatiry(){
        return populatiry;
    }

    public void setPopulatiry(Double populatiry){
        this.populatiry = populatiry;
    }

    public Integer getVoteCount(){
        return voteCount;
    }

    public void setVoteCount(Integer voteCount){
        this.voteCount = voteCount;
    }

    public Boolean getVideo(){
        return video;
    }

    public void setVideo(Boolean video){
        this.video = video;
    }

    public Double getVoteAvarage(){
        return voteAvarage;
    }

    public void setVoteAvarage(Double voteAvarage){
        this.voteAvarage = voteAvarage;
    }

}
















