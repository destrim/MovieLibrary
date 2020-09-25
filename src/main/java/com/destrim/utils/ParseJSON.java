package com.destrim.utils;

import com.destrim.movie.representation.Movie;

import org.json.JSONObject;

public class ParseJSON {
    public static Movie parse(String jsonFromApi) {
        JSONObject obj = new JSONObject(jsonFromApi);
        return new Movie(
                obj.getString("Title"),
                obj.getString("Year"),
                obj.getString("Genre"),
                obj.getString("Plot"),
                obj.getString("imdbRating")
        );
    }
}
