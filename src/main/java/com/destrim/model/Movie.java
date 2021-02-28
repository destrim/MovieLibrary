package com.destrim.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String title;
    private String released;
    private String genre;
    private String plot;
    private String imdbRating;


    public Movie(String title, String released, String genre, String plot, String imdbRating) {
        this.title = title;
        this.released = released;
        this.genre = genre;
        this.plot = plot;
        this.imdbRating = imdbRating;
    }
}
