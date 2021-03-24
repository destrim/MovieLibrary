package com.destrim.util;

import com.destrim.model.MovieDTO;
import com.google.gson.Gson;
import lombok.Data;
import org.json.JSONObject;

@Data
public class ParseJSON {

    public MovieDTO parse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, MovieDTO.class);
    }

    public boolean isResponseCorrect(String response) {
        JSONObject obj = new JSONObject(response);
        return obj.getBoolean("Response");
    }
}
