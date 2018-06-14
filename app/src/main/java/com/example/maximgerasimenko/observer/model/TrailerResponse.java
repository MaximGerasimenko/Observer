package com.example.maximgerasimenko.observer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


//Pobieranie oraz wyswietlanie listy trejlerow.
public class TrailerResponse {

    @SerializedName("id")
    private int id_trailer;
    @SerializedName("results")
    private List<Trailer> results;

    //Metoda pobierania listy trejlerow
    public List<Trailer> getResults(){
        return results;
    }
}
