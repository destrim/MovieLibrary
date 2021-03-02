package com.destrim.util;

import com.destrim.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileHandling {

    public static void exportToFile(List<Movie> movies, String fileName) throws IOException {
        JSONArray arr = getJsonArrayFromMovies(movies);
        saveJsonArrayToFile(fileName, arr);
    }

    public static List<Movie> importFromFile(String fileName) throws IOException {
        JSONArray arr = parseJsonArrayFromFile(fileName);
        return getMoviesFromJsonArray(arr);
    }

    public static Optional<String> importApikey() {
        Path path = Path.of("src/main/resources/apikey");

        try {
            return Optional.of(Files.readString(path));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static JSONArray getJsonArrayFromMovies(List<Movie> movies) {
        JSONArray arr = new JSONArray();

        for (Movie movie : movies) {
            JSONObject obj = getJsonObjectFromMovie(movie);
            arr.put(obj);
        }
        return arr;
    }

    private static void saveJsonArrayToFile(String fileName, JSONArray arr) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fileName + ".json");
        pw.write(arr.toString(3));
        pw.flush();
        pw.close();
    }

    private static JSONArray parseJsonArrayFromFile(String fileName) throws IOException {
        Path path = Path.of(fileName + ".json");
        String jsonFromFile = Files.readString(path);
        return new JSONArray(jsonFromFile);
    }

    private static List<Movie> getMoviesFromJsonArray(JSONArray arr) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            Movie movie = getMovieFromJsonObject(arr, i);
            movies.add(movie);
        }
        return movies;
    }

    private static JSONObject getJsonObjectFromMovie(Movie movie) {
        Map<String, String> m = new HashMap<>(5);
        m.put("Title", movie.getTitle());
        m.put("Released", movie.getReleased());
        m.put("Genre", movie.getGenre());
        m.put("Plot", movie.getPlot());
        m.put("imdbRating", movie.getImdbRating());
        return new JSONObject(m);
    }

    private static Movie getMovieFromJsonObject(JSONArray arr, int i) {
        JSONObject obj = arr.getJSONObject(i);

        return new Movie(
                obj.getString("Title"),
                obj.getString("Released"),
                obj.getString("Genre"),
                obj.getString("Plot"),
                obj.getString("imdbRating")
        );
    }
}
