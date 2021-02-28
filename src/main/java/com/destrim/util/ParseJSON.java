package com.destrim.util;

import com.destrim.model.Movie;

import org.json.JSONObject;

public class ParseJSON {
    public static Movie parse(String response) {
        JSONObject obj = new JSONObject(response);
        return new Movie(
                obj.getString("Title"),
                obj.getString("Year"),
                obj.getString("Genre"),
                obj.getString("Plot"),
                obj.getString("imdbRating")
        );
    }

    public static boolean isResponseCorrect(String response) {
        JSONObject obj = new JSONObject(response);
        return obj.getBoolean("Response");
    }
}
