package com.teams.favoriteteamsnba.exception;

public class TimeNotFoundException extends RuntimeException {

    public TimeNotFoundException(Long id) {
        super("Time com id " + id + " não encontrado");
    }

    public TimeNotFoundException(String message) {
        super(message);
    }
}
