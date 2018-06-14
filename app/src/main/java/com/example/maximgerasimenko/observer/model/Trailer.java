package com.example.maximgerasimenko.observer.model;

import com.google.gson.annotations.SerializedName;

//Pobieranie oraz ustawienie informacji o trejlerach
public class Trailer {
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;

    //Constructor
    public Trailer(String key, String name){
        this.key = key;
        this.name = name;
    }

    //Pobieranie kluczu
    public String getKey(){
        return key;
    }

    //Pobieranie nazwy
    public String getName(){
        return name;
    }

    //Ustawienie nazwy
    public void setName(String name){
        this.name = name;
    }
}
