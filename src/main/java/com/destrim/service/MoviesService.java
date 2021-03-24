package com.destrim.service;

import com.destrim.exception.BadApikeyException;
import com.destrim.exception.MovieInOmdbNotFound;
import com.destrim.exception.OmdbConnectionProblem;
import com.destrim.model.Movie;
import com.destrim.model.MovieDTO;
import com.destrim.persistance.MovieRepository;
import com.destrim.util.FileHandling;
import com.destrim.util.MovieMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MoviesService {

    private final MovieRepository movieRepository;
    private final OmdbWebServiceClient omdbWebServiceClient;
    private final FileHandling fileHandling;

    public MoviesService() throws BadApikeyException {
        this.movieRepository = new MovieRepository();
        this.omdbWebServiceClient = new OmdbWebServiceClient();
        this.fileHandling = new FileHandling();
    }

    public List<MovieDTO> getMovies() {
        List<Movie> movies = movieRepository.getAll();
        return movies.stream()
                .map(MovieMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addMovie(MovieDTO movieDTO) {
        Movie movie = MovieMapper.mapFromDTO(movieDTO);
        movieRepository.add(movie);
    }

    public MovieDTO fetchMovieData(String title, String year) throws MovieInOmdbNotFound, OmdbConnectionProblem {
        return omdbWebServiceClient.searchMovieByTitleYear(title, year);
    }

    public void deleteMovie(long id) {
        movieRepository.delete(id);
    }

    public MovieDTO getMovie(long id) {
        Movie movie = movieRepository.get(id);
        return MovieMapper.mapToDTO(movie);
    }

    public void importMoviesFromJSON(String fileName) throws IOException {
        List<MovieDTO> moviesDTO = fileHandling.importFromFile(fileName);
        List<Movie> movies = moviesDTO.stream()
                .map(MovieMapper::mapFromDTO)
                .collect(Collectors.toList());

        movieRepository.addAll(movies);
    }

    public void exportMoviesToJSON(String fileName) throws IOException {
        List<Movie> movies = movieRepository.getAll();
        List<MovieDTO> moviesDTO = movies.stream()
                .map(MovieMapper::mapToDTO)
                .collect(Collectors.toList());

        fileHandling.exportToFile(moviesDTO, fileName);
    }
}
