package com.destrim.movie;

import com.destrim.utils.SortHelper;
import com.destrim.service.OmdbWebServiceClient;
import com.destrim.movie.representation.Movie;
import com.destrim.utils.FileHandling;
import com.destrim.utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MoviesLibrary {
    public ArrayList<Movie> movies;

    public MoviesLibrary() {
        this.movies = new ArrayList<Movie>();
    }

    public void searchForMovie() {
        Movie searchForMovie = ReadInputData.readMovieAndYear();

        String jsonFromApi = OmdbWebServiceClient.searchMovieByTitleYear(searchForMovie.getTitle(),searchForMovie.getReleased());

        Movie movie = ParseJSON.parse(jsonFromApi);

        movies.add(movie);
    }

    public void deleteMovie() {
        ArrayList<Movie> moviesToDelete = new ArrayList<Movie>();

        Movie deleteMovie = ReadInputData.readMovieAndYear();

        for (Movie movie : movies) {
            boolean isTitleCorr = movie.getTitle().toLowerCase().contains(deleteMovie.getTitle().toLowerCase());
            boolean isYearCorr = movie.getReleased().equals(deleteMovie.getReleased());

            if ((isTitleCorr && isYearCorr) || (isTitleCorr && deleteMovie.getReleased().equals(""))) {
                moviesToDelete.add(movie);
            }
        }

        for (Movie movie : moviesToDelete) {
            StringBuilder messageToPrint = new StringBuilder()
                    .append("\nMovie title: ")
                    .append(movie.getTitle())
                    .append("\n")
                    .append("Movie released: ")
                    .append(movie.getReleased())
                    .append("\n")
                    .append("Are you sure you want to delete this movie from your list?");

            System.out.println(messageToPrint.toString());
            char choice = ReadInputData.readYesOrNo();

            if (choice == 'y') {
                movies.remove(movie);
                break;
            }
        }

        if (moviesToDelete.isEmpty())
            System.out.println("Movie not found in your list.");
    }

    public void showMovies() {
        int moviecount = 1;
        for (Movie movie : movies) {
            StringBuilder messageToPrint = new StringBuilder()
                    .append("\nMovie No.")
                    .append(moviecount)
                    .append("\n\tMovie title: ")
                    .append(movie.getTitle())
                    .append("\n\tMovie released: ")
                    .append(movie.getReleased())
                    .append("\n\tMovie genre: ")
                    .append(movie.getGenre())
                    .append("\n\tMovie plot: ")
                    .append(movie.getPlot())
                    .append("\n\tMovie IMDB Rating: ")
                    .append(movie.getImdbRating());
            System.out.println(messageToPrint.toString());
            moviecount++;
        }
    }

    public void sortMovies() {
        StringBuilder messageToPrint = new StringBuilder()
                .append("\nYou want your movies sorted by: \n")
                .append("\t1. Title.\n")
                .append("\t2. Year released.\n")
                .append("\t3. IMDB rating.\n")
                .append("\n");

        System.out.print(messageToPrint.toString());
        String choice = ReadInputData.readWhatToDo();

        switch (choice) {
            case "1" -> movies = SortHelper.SortMoviesByTitle(movies);
            case "2" -> movies = SortHelper.SortMoviesByYear(movies);
            case "3" -> movies = SortHelper.SortMoviesByRating(movies);
            default -> System.out.println("Incorrect number.");
        }
    }

    public void saveMovies() {
        try {
            FileHandling.saveToFile(movies);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save movies to file.");
        }
    }

    public void importMovies() {
        try {
            FileHandling.importFromFile(movies);
        } catch (IOException e) {
            System.out.println("Cannot import movies from the file.");
        }
    }
}
