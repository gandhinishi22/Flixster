package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    // Usually involves inflating a layout from XML and returning the holder (expensive)
    /*@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);

        return new ViewHolder(movieView);
    }*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View movieView;
        switch(viewType)
        {
            case 1:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
                holder = new ViewHolder(movieView);
                break;
            case 0:
                movieView = LayoutInflater.from(context).inflate(R.layout.item_pop,parent,false);
                holder = new ViewHolder1(movieView);
                break;
            default:
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        switch(holder.getItemViewType())
        {
            case 1:
                ViewHolder movieView = (ViewHolder) holder;
                movieView.bind(movie);
                break;
            case 0:
                ViewHolder1 movieView1 = (ViewHolder1) holder;
                movieView1.bindIt(movie);
                break;
            default:
                break;
        }
    }

    /*@Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
    }*/

    //Involves populating data into the item through holder (cheap)
    /*@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder" + position);
        //Get the movie at the passed position
        Movie movie = movies.get(position);
        //Bind the movie data in the View Holder
        holder.bind(movie);
    }*/

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getVote() > 8) {
            return 0;
        }
        return 1;
        //return super.getItemViewType(position);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageURL;

            //if phone in landscape then imageURL = backdrop
            //else default is poster image
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackdropPath();
            }
            else {
                imageURL = movie.getPosterPath();
            }

            int radius = 30;
            int margin = 5;

            Glide.with(context).load(imageURL).placeholder(R.drawable.hour).transform(new RoundedCornersTransformation(radius,margin)).into(ivPoster);
            //Glide.with(context).load(imageURL).into(ivPoster);


            //1. Register the click listener on the whole row

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //2. Navigate to a new activity on tap
                    //Toast.makeText(context,movie.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    //i.putExtra("title",movie.getTitle());
                    i.putExtra("movie", Parcels.wrap(movie));

                    context.startActivity(i);
                }
            });
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        RelativeLayout container;
        ImageView ivPoster;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bindIt(Movie movie)
        {
            String imageURL;
            imageURL = movie.getBackdropPath();
            int rad = 30;
            int mar = 5;
            Glide.with(context).load(imageURL).placeholder(R.drawable.hour).transform(new RoundedCornersTransformation(rad,mar)).into(ivPoster);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //2. Navigate to a new activity on tap
                    //Toast.makeText(context,movie.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    //i.putExtra("title",movie.getTitle());
                    i.putExtra("movie", Parcels.wrap(movie));

                    context.startActivity(i);
                }
            });
        }


    }
}
