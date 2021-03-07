package com.destrim.util;

import com.destrim.model.MovieDTO;
import org.json.JSONObject;

public class ParseJSON {
    public static MovieDTO parse(String response) {
        JSONObject obj = new JSONObject(response);
        return new MovieDTO(
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
