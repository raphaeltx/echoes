package com.raph.resistance_echoes.exception;

/**
 * Custom exception class for handling errors related to saving echoes.
 */
public class SaveEchoesException extends Exception {

    public SaveEchoesException(String messagem, Throwable cause) {
        super(messagem, cause);
    }
}
