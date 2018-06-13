package com.example.maximgerasimenko.observer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);
    }
}
