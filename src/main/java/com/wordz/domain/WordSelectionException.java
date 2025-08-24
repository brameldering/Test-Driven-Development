package com.wordz.domain;

public class WordSelectionException extends RuntimeException {
    public WordSelectionException(String message, Throwable t) {
        super(message, t);
    }
}
