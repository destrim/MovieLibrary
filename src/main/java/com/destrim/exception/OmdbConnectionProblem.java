package com.destrim.exception;

public class OmdbConnectionProblem extends Exception{

    public OmdbConnectionProblem() {
        super("There was a connection problem with OMDb.");
    }
}
