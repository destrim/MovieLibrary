package com.destrim.util;

import com.destrim.model.Movie;
import com.destrim.model.MovieDTO;

public class MovieMapper {

    public static MovieDTO mapToDTO(Movie movie) {
        return  MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .released(movie.getReleased())
                .genre(movie.getGenre())
                .plot(movie.getPlot())
                .imdbRating(movie.getImdbRating())
                .build();
    }

    public static Movie mapFromDTO(MovieDTO movieDTO) {
        return Movie.builder()
                .title(movieDTO.getTitle())
                .released(movieDTO.getReleased())
                .genre(movieDTO.getGenre())
                .plot(movieDTO.getPlot())
                .imdbRating(movieDTO.getImdbRating())
                .build();
    }
}
