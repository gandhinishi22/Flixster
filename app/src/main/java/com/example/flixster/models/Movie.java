package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int movieId;
    String releaseDate;
    boolean isAdult;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double pop;
    double vote;


    //empty constructor needed by Parceler library
    public Movie() { }

    //Parse data
    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        vote = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
        pop = jsonObject.getDouble("popularity");
        releaseDate = jsonObject.getString("release_date");
        isAdult = jsonObject.getBoolean("adult");

    }

    //constructs movie for each element in JSON Array
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i <movieJsonArray.length(); i++)
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public double getVote() {  return vote; }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public double getPop() { return pop; }
}
