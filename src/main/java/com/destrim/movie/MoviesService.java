package com.destrim.movie;

import com.destrim.persistance.MovieRepository;
import com.destrim.utils.SortHelper;
import com.destrim.service.OmdbWebServiceClient;
import com.destrim.movie.representation.Movie;
import com.destrim.utils.FileHandling;
import com.destrim.utils.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoviesService {

    private final MovieRepository movieRepository;

    public MoviesService() {
        this.movieRepository = new MovieRepository();
    }

    public void showMovies() {
        List<Movie> movies = movieRepository.getAll();
        for (Movie movie : movies) {
            StringBuilder messageToPrint = new StringBuilder()
                    .append("\nMovie No.")
                    .append(movie.getId())
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
        }
    }  // done for DB

    public void addMovie(String[] command) {
        String title = "";
        String year = "";

        for (int i = 1; i < command.length; i++) {
            String[] arg = command[i].split(" ");

            if(arg[0].equalsIgnoreCase("t")) {
                title = arg[1];
                for (int j = 2; j < arg.length; j++) {
                    title = String.join(" ", title, arg[j]);
                }
            }
            else if(arg[0].equalsIgnoreCase("y")) {
                if (arg.length > 1) {
                    year = arg[1];
                }
            }
        }

        String jsonFromApi = OmdbWebServiceClient.searchMovieByTitleYear(title, year);

        Movie movie = ParseJSON.parse(jsonFromApi);

        StringBuilder messageToPrint = new StringBuilder()
                .append("\nAre you sure you want to add movie:")
                .append("\n\tMovie title: ")
                .append(movie.getTitle())
                .append("\n\tMovie released: ")
                .append(movie.getReleased())
                .append("\n\tMovie genre: ")
                .append(movie.getGenre())
                .append("\n\tMovie plot: ")
                .append(movie.getPlot())
                .append("\n\tMovie IMDB Rating: ")
                .append(movie.getImdbRating())
                .append("\nto your database?\n");

        System.out.println(messageToPrint.toString());

        char choice = ReadInputData.readYesOrNo();
        if (choice == 'y') {
            movieRepository.add(movie);
        }
    }  // done for DB

//    public void deleteMovie() {
//        ArrayList<Movie> moviesToDelete = new ArrayList<>();
//
//        Movie deleteMovie = ReadInputData.readMovieAndYear();
//
//        for (Movie movie : movies) {
//            boolean isTitleCorr = movie.getTitle().toLowerCase().contains(deleteMovie.getTitle().toLowerCase());
//            boolean isYearCorr = movie.getReleased().equals(deleteMovie.getReleased());
//
//            if ((isTitleCorr && isYearCorr) || (isTitleCorr && deleteMovie.getReleased().equals(""))) {
//                moviesToDelete.add(movie);
//            }
//        }
//
//        for (Movie movie : moviesToDelete) {
//            StringBuilder messageToPrint = new StringBuilder()
//                    .append("\nMovie title: ")
//                    .append(movie.getTitle())
//                    .append("\n")
//                    .append("Movie released: ")
//                    .append(movie.getReleased())
//                    .append("\n")
//                    .append("Are you sure you want to delete this movie from your list?");
//
//            System.out.println(messageToPrint.toString());
//            char choice = ReadInputData.readYesOrNo();
//
//            if (choice == 'y') {
//                movies.remove(movie);
//                break;
//            }
//        }
//
//        if (moviesToDelete.isEmpty())
//            System.out.println("Movie not found in your list.");
//    }

    public void deleteMovie(String[] command) {
        String[] arg = command[1].split(" ");

        if(arg[0].equalsIgnoreCase("i") && arg.length > 1) {
            long id = Long.parseLong(arg[1]);

            Movie movie = movieRepository.getById(id);

            StringBuilder messageToPrint = new StringBuilder()
                    .append("\nAre you sure you want to delete movie:")
                    .append("\n\tMovie title: ")
                    .append(movie.getTitle())
                    .append("\n\tMovie released: ")
                    .append(movie.getReleased())
                    .append("\n\tMovie genre: ")
                    .append(movie.getGenre())
                    .append("\n\tMovie plot: ")
                    .append(movie.getPlot())
                    .append("\n\tMovie IMDB Rating: ")
                    .append(movie.getImdbRating())
                    .append("\nfrom your database?\n");

            System.out.println(messageToPrint);

            char choice = ReadInputData.readYesOrNo();
            if (choice == 'y') {
                movieRepository.delete(id);
            }
        }
    }  // done for DB

//    public void sortMovies() {
//        StringBuilder messageToPrint = new StringBuilder()
//                .append("\nYou want your movies sorted by: \n")
//                .append("\t1. Title.\n")
//                .append("\t2. Year released.\n")
//                .append("\t3. IMDB rating.\n")
//                .append("\n");
//
//        System.out.print(messageToPrint.toString());
//        String choice = ReadInputData.readWhatToDo();
//
//        switch (choice) {
//            case "1" -> movies = SortHelper.SortMoviesByTitle(movies);
//            case "2" -> movies = SortHelper.SortMoviesByYear(movies);
//            case "3" -> movies = SortHelper.SortMoviesByRating(movies);
//            default -> System.out.println("Incorrect number.");
//        }
//    }

    public void exportMoviesToJSON(String[] command) {
        String[] arg = command[1].split(" ");

        if(arg[0].equalsIgnoreCase("f") && arg.length > 1) {
            List<Movie> movies = movieRepository.getAll();
            String fileName = arg[1];

            try {
                FileHandling.saveToFile(movies, fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }  // done for DB

    public void importMoviesFromJSON(String[] command) {
        String[] arg = command[1].split(" ");

        if(arg[0].equalsIgnoreCase("f") && arg.length > 1) {
            String fileName = arg[1];

            try {
                List<Movie> movies = FileHandling.importFromFile(fileName);
                movieRepository.addAll(movies);
            } catch (IOException e) {
                System.out.println("Cannot import movies from the file.");
            }
        }
    }  // done for DB
}
