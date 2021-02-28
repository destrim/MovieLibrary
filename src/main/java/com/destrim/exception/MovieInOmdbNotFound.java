package com.destrim.exception;

public class MovieInOmdbNotFound extends Exception{

    public MovieInOmdbNotFound() {
        super("Movie not found in OMDb.");
    }
}
