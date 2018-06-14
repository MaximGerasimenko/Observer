package com.example.maximgerasimenko.observer.api;

import com.example.maximgerasimenko.observer.model.MoviesResponse;
import com.example.maximgerasimenko.observer.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    //Wyswietlenie najpopularniejszych filmow.
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    //wyswietlenie filmow z najwieksa ocena.
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    //Wyswietlenie trejlera.
    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
