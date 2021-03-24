package com.destrim.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    private transient long id;

    @SerializedName(value = "Title")
    private String title;
    @SerializedName(value = "Year")
    private String year;
    @SerializedName(value = "Genre")
    private String genre;
    @SerializedName(value = "Plot")
    private String plot;
    @SerializedName(value = "imdbRating")
    private String imdbRating;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private long id;
        private String title;
        private String year;
        private String genre;
        private String plot;
        private String imdbRating;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder year(String year) {
            this.year = year;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder plot(String plot) {
            this.plot = plot;
            return this;
        }

        public Builder imdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
            return this;
        }

        public MovieDTO build() {
            if (title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }

            if (year.isEmpty()) {
                throw new IllegalArgumentException("Released year cannot be empty");
            }

            MovieDTO movieDTO = new MovieDTO();
            movieDTO.id = this.id;
            movieDTO.title = this.title;
            movieDTO.year = this.year;
            movieDTO.genre = this.genre;
            movieDTO.plot = this.plot;
            movieDTO.imdbRating = this.imdbRating;
            return movieDTO;
        }
    }
}
