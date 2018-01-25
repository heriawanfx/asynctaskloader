package com.heriawanfx.asynctaskloader;

import org.json.JSONObject;


/**
 * Created by user on 25-01-2018.
 */

public class MovieItems {
    private int id;
    private String title;
    private String releaseDate;
    private String overview;
    private String posterPath;
    private Double voteAverage;
    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String releaseDate = object.getString("release_date");
            String posterPath = object.getString("poster_path");
            String overview = object.getString("overview");
            Double voteAverage = Double.valueOf(object.getString("vote_average"));
            this.id = id;
            this.title = title;
            this.releaseDate = releaseDate;
            this.posterPath = posterPath;
            this.overview = overview;
            this.voteAverage = voteAverage;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}