package com.example.maximgerasimenko.observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maximgerasimenko.observer.adapter.TrailerAdapter;
import com.example.maximgerasimenko.observer.api.Client;
import com.example.maximgerasimenko.observer.api.Service;
import com.example.maximgerasimenko.observer.data.FavoriteDbHelper;
import com.example.maximgerasimenko.observer.model.Movie;
import com.example.maximgerasimenko.observer.model.Trailer;
import com.example.maximgerasimenko.observer.model.TrailerResponse;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    //Tekstowe zmienne
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    //Zmienne z urzyciem plikow.
    ImageView imageView;
    //Zmiena RecyclerView
    private RecyclerView recyclerView;
    //Zmiena TrailerAdapter
    private TrailerAdapter adapter;
    //Lista trejlerow
    private List<Trailer> trailerList;
    private FavoriteDbHelper favoriteDbHelper;
    private Movie favorite;
    private final AppCompatActivity activity = DetailActivity.this;

    Movie movie;
    String thumbnail, movieName, synopsis, rating, dateOfRelease;
    int movie_id;

    //Metoda onCreate (tworzy widok)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        //Przypisanie do zmiennych ich miejsca w layoucie.
        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        nameOfMovie = (TextView) findViewById(R.id.title);
        plotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        releaseDate = (TextView) findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("original_title")){
            //Pobieranie danych o filmie
            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            //Pobieranie posteru filmu
            String poster = "https://image.tmdb.org/t/p/w500" + thumbnail;

            //Wyswietlenie postera
            Glide
                    .with(this)
                    .load(thumbnail)
                    .into(imageView);


            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);
            ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar)).setTitle(movieName);

        }else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }

        MaterialFavoriteButton materialFavoriteButtonNice =
                (MaterialFavoriteButton) findViewById(R.id.favorite_button);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Metoda  zapisujaca film do ulubionych
        materialFavoriteButtonNice.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener(){
                    //Metoda  sprawdzania czy byl film dodany do ulubionych
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite){
                        if (favorite){
                            SharedPreferences.Editor editor = getSharedPreferences("com.example.maximgerasimenko.observer.DetailActivity", MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Added", true);
                            editor.apply();
                            saveFavorite();
                            Snackbar.make(buttonView, "Added to Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                        }else{
                            int movie_id = getIntent().getExtras().getInt("id");
                            favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                            favoriteDbHelper.deleteFavorite(movie_id);

                            SharedPreferences.Editor editor = getSharedPreferences("com.example.maximgerasimenko.observer.DetailActivity", MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Removed", true);
                            editor.apply();
                            Snackbar.make(buttonView, "Removed from Favorite", Snackbar.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        initViews();

    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    //Wyswietlenie informacji o filmie oraz trajlerie.
    private void initViews(){
        trailerList = new ArrayList<>();
        adapter = new TrailerAdapter(this, trailerList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON();

    }

    //Pobieranie informacji oraz wyswietlenie trejlerow.
    private void loadJSON(){
        int movie_id = getIntent().getExtras().getInt("id");
        try{
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<TrailerResponse> call = apiService.getMovieTrailer(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<TrailerResponse>() {

                //Pobieranie listy trejlerow
                @Override
                public void onResponse( Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    List<Trailer> trailer = response.body().getResults();
                    recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));
                    recyclerView.smoothScrollToPosition(0);
                }
                //Jezeli mamy blad przy odczytywaniu
                @Override
                public void onFailure(@Nullable Call<TrailerResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetailActivity.this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //Metoda zapisujaca film do ulubionych.
    public void saveFavorite(){
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Movie();
        int movie_id = getIntent().getExtras().getInt("id");
        String rate = getIntent().getExtras().getString("vote_average");
        String poster = getIntent().getExtras().getString("poster_path");
        favorite.setId(movie_id);
        favorite.setOriginalTitle(nameOfMovie.getText().toString().trim());
        favorite.setPosterPath(poster);
        favorite.setVoteAverage(Double.parseDouble(rate));
        favorite.setOverview(plotSynopsis.getText().toString().trim());
        favoriteDbHelper.addFavorite(favorite);
    }

}
