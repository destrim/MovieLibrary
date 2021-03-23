package com.destrim.util;

import com.destrim.model.MovieDTO;
import lombok.Data;
import org.json.JSONObject;

@Data
public class ParseJSON {

    public MovieDTO parse(String response) {
        JSONObject obj = new JSONObject(response);
        return MovieDTO.builder()
                .title(obj.getString("Title"))
                .released(obj.getString("Year"))
                .genre(obj.getString("Genre"))
                .plot(obj.getString("Plot"))
                .imdbRating(obj.getString("imdbRating"))
                .build();
    }

    public boolean isResponseCorrect(String response) {
        JSONObject obj = new JSONObject(response);
        return obj.getBoolean("Response");
    }
}
