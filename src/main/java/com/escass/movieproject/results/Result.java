package com.escass.movieproject.results;

public interface Result {
    String NAME = "result";
    String NAMES = "results";
    String RESULT = "session";

    String name();

    default String nameToLower() {
        return this.name().toLowerCase();
    }
}
