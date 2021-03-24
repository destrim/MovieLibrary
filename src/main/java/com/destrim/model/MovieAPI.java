package com.destrim.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieAPI {

    @SerializedName(value = "Title")
    private String title;
    @SerializedName(value = "Year")
    private String year;
    @SerializedName(value = "Genre")
    private String genre;
    @SerializedName(value = "Plot")
    private String plot;
    @SerializedName(value = "imdbRating")
    private String imdbRating;
}
