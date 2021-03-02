package com.destrim.service;

import com.destrim.exception.BadApikeyException;
import com.destrim.exception.MovieInOmdbNotFound;
import com.destrim.exception.OmdbConnectionProblem;
import com.destrim.model.Movie;
import com.destrim.persistance.MovieRepository;
import com.destrim.util.FileHandling;

import java.io.IOException;
import java.util.List;

public class MoviesService {

    private final MovieRepository movieRepository;
    private final OmdbWebServiceClient omdbWebServiceClient;
    private final FileHandling fileHandling;

    public MoviesService() throws BadApikeyException {
        this.movieRepository = new MovieRepository();
        this.omdbWebServiceClient = new OmdbWebServiceClient();
        this.fileHandling = new FileHandling();
    }

    public List<Movie> getMovies() {
        return movieRepository.getAll();
    }

    public void addMovie(Movie movie) {
        movieRepository.add(movie);
    }

    public Movie fetchMovieData(String title, String year) throws MovieInOmdbNotFound, OmdbConnectionProblem {
        return omdbWebServiceClient.searchMovieByTitleYear(title, year);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie getMovie(long id) {
        return movieRepository.get(id);
    }

    public void importMoviesFromJSON(String fileName) throws IOException {
        List<Movie> movies = fileHandling.importFromFile(fileName);
        movieRepository.addAll(movies);
    }

    public void exportMoviesToJSON(String fileName) throws IOException {
        List<Movie> movies = movieRepository.getAll();
        fileHandling.exportToFile(movies, fileName);
    }
}
