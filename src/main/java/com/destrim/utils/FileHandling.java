package com.destrim.utils;

import com.destrim.movie.representation.Movie;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileHandling {
    public static void saveToFile(ArrayList<Movie> movies) throws FileNotFoundException {
        JSONObject obj = new JSONObject();
        int i = 1;
        for (Movie movie : movies) {
            Map m = new LinkedHashMap(5);
            m.put("Title", movie.getTitle());
            m.put("Released", movie.getReleased());
            m.put("Genre", movie.getGenre());
            m.put("Plot", movie.getPlot());
            m.put("imdbRating", movie.getImdbRating());

            obj.put("movieNo" + i, m);
            i++;
        }

        String fileName = ReadInputData.readFileName();
        PrintWriter pw = new PrintWriter(fileName + ".json");
        pw.write(obj.toString());
        pw.flush();
        pw.close();
    }

    public static void importFromFile(ArrayList<Movie> movies) throws IOException {
        String fileName = ReadInputData.readFileName();

        Path path = Path.of(fileName + ".json");
        String jsonFromFile = Files.readString(path);

        String findStr = "movieNo";
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = jsonFromFile.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }

        for (int i = 1; i <= count; i++) {
            JSONObject obj = new JSONObject(jsonFromFile);
            String countMovies = Integer.toString(i);

            Movie movie = new Movie(
                    obj.getJSONObject(findStr + countMovies).getString("Title"),
                    obj.getJSONObject(findStr + countMovies).getString("Released"),
                    obj.getJSONObject(findStr + countMovies).getString("Genre"),
                    obj.getJSONObject(findStr + countMovies).getString("Plot"),
                    obj.getJSONObject(findStr + countMovies).getString("imdbRating")
            );

            movies.add(movie);
        }
    }
}
