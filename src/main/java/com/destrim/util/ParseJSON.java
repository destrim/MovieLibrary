package com.destrim.util;

import com.destrim.model.MovieAPI;
import com.destrim.model.MovieDTO;
import com.google.gson.Gson;
import lombok.Data;
import org.json.JSONObject;

@Data
public class ParseJSON {

    public MovieDTO parse(String response) {
        Gson gson = new Gson();
        MovieAPI movieAPI = gson.fromJson(response, MovieAPI.class);
        return MovieDTO.builder()
                .title(movieAPI.getTitle())
                .year(movieAPI.getYear())
                .genre(movieAPI.getGenre())
                .plot(movieAPI.getPlot())
                .imdbRating(movieAPI.getImdbRating())
                .build();
    }

    public boolean isResponseCorrect(String response) {
        JSONObject obj = new JSONObject(response);
        return obj.getBoolean("Response");
    }
}
