package com.example.maximgerasimenko.observer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Pobieranie informacji o filmach przez JSON.
public class MoviesResponse {
    @SerializedName("results")
    private List<Movie> results;
    //Pobieranie filmow
    public List<Movie> getResults(){
        return results;
    }

}
