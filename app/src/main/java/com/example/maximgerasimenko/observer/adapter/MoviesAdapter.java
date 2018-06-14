package com.example.maximgerasimenko.observer.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maximgerasimenko.observer.DetailActivity;
import com.example.maximgerasimenko.observer.R;
import com.example.maximgerasimenko.observer.model.Movie;

import java.io.Serializable;
import java.util.List;
//
//Clasa pozwalajaca na wyswietlenie oraz pobieranie informacji i filmie.
//
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;
    public MoviesAdapter(Context mContext, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i){
    viewHolder.title.setText(movieList.get(i).getOriginalTitle());
    String vote = Double.toString(movieList.get(i).getVoteAverage());
    viewHolder.userrating.setText(vote);

    Glide.with(mContext)
            .load(movieList.get(i).getPosterPath())
            .into(viewHolder.thumbnail);
    }

    //Zwracanie liczby elementow w liscie
    @Override
    public int getItemCount(){
        return movieList.size();
    }

    //Klasa MyViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            userrating = view.findViewById(R.id.userrating);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener(){
                //Metoda onClick (Pobieranie informacji po kliknieciu)
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent  = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        intent.putExtra("id", movieList.get(pos).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT);

                    }
                }
            });
        }
    }
}
