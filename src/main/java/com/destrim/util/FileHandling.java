package com.destrim.util;

import com.destrim.model.Movie;
import com.destrim.model.MovieDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class FileHandling {

    private static final String pathString = "src/main/resources/apikey";
    private static final String jsonFormat = ".json";

    public void exportToFile(List<MovieDTO> moviesDTO, String fileName) throws IOException {
        String json = getJsonFromMovies(moviesDTO);
        saveJsonToFile(fileName, json);
    }

    public List<MovieDTO> importFromFile(String fileName) throws IOException {
        String json = parseJsonFromFile(fileName);
        return getMoviesFromJson(json);
    }

    public static Optional<String> importApikey() {
        Path path = Path.of(pathString);

        try {
            return Optional.of(Files.readString(path));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String getJsonFromMovies(List<MovieDTO> moviesDTO) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(moviesDTO);
    }

    private void saveJsonToFile(String fileName, String json) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fileName + jsonFormat);
        pw.write(json);
        pw.flush();
        pw.close();
    }

    private String parseJsonFromFile(String fileName) throws IOException {
        Path path = Path.of(fileName + jsonFormat);
        return Files.readString(path);
    }

    private List<MovieDTO> getMoviesFromJson(String json) {
        Gson gson = new Gson();

        Type movieListType = new TypeToken<List<MovieDTO>>() {
        }.getType();

        return gson.fromJson(json, movieListType);
    }
}
