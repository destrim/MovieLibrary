package com.destrim.util;

import com.destrim.model.Movie;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileHandling {
    public static void exportToFile(List<Movie> movies, String fileName) throws IOException {
        JSONObject obj = new JSONObject();
        int i = 1;
        for (Movie movie : movies) {
            Map<String, String> m = new LinkedHashMap<>(5);
            m.put("Title", movie.getTitle());
            m.put("Released", movie.getReleased());
            m.put("Genre", movie.getGenre());
            m.put("Plot", movie.getPlot());
            m.put("imdbRating", movie.getImdbRating());

            obj.put("movieNo" + i, m);
            i++;
        }

        PrintWriter pw = new PrintWriter(fileName + ".json");
        pw.write(obj.toString());  // TODO json formatting
        pw.flush();
        pw.close();
    }

    public static ArrayList<Movie> importFromFile(String fileName) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

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
            obj = obj.getJSONObject(findStr + countMovies);

            Movie movie = new Movie(
                    obj.getString("Title"),
                    obj.getString("Released"),
                    obj.getString("Genre"),
                    obj.getString("Plot"),
                    obj.getString("imdbRating")
            );

            movies.add(movie);
        }
        return movies;
    }

    public static Optional<String> importApikey() {
        Path path = Path.of("src/main/resources/apikey");

        try {
            return Optional.of(Files.readString(path));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
