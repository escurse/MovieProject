package com.escass.movieproject.results;

public interface Result {
    String NAME = "result";
    String RESULT = "theater";

    String name();

    default String nameToLower() {
        return this.name().toLowerCase();
    }
}
