package com.wordz.domain;

public class WordRepositoryException extends RuntimeException {

    public WordRepositoryException() {
        super();
    }

    public WordRepositoryException(String message) {
        super(message);
    }
}
