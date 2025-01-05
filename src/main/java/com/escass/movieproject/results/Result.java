package com.escass.movieproject.results;

public interface Result {
    String NAME = "result";
    String NAMES = "results";


    String name();

    default String nameToLower() {
        return this.name().toLowerCase();
    }
}
