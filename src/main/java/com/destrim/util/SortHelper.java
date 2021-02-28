package com.destrim.util;

import com.destrim.model.Movie;
import java.util.ArrayList;
import java.util.Collections;

public class SortHelper {
    public static ArrayList<Movie> SortMoviesByTitle(ArrayList<Movie> movies) {
        int el = movies.size();
        for (int i = 0; i < el; i++) {
            for (int j = 1; j < el - i; j++) {
                if (movies.get(j - 1).getTitle().compareToIgnoreCase(movies.get(j).getTitle()) > 0) {
                    Collections.swap(movies, j - 1, j);
                }
            }
        }
        return movies;
    }

    public static ArrayList<Movie> SortMoviesByYear(ArrayList<Movie> movies) {
        int el = movies.size();
        for (int i = 0; i < el; i++) {
            for (int j = 1; j < el - i; j++) {
                if (movies.get(j - 1).getReleased().compareToIgnoreCase(movies.get(j).getReleased()) > 0) {
                    Collections.swap(movies, j - 1, j);
                }
            }
        }
        return movies;
    }

    public static ArrayList<Movie> SortMoviesByRating(ArrayList<Movie> movies) {
        int el = movies.size();
        for (int i = 0; i < el; i++) {
            for (int j = 1; j < el - i; j++) {
                if (movies.get(j - 1).getImdbRating().compareToIgnoreCase(movies.get(j).getImdbRating()) < 0) {
                    Collections.swap(movies, j - 1, j);
                }
            }
        }
        return movies;
    }
}
