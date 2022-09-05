package ru.aryunin.UrlShortener.Utils;

public class LongUrlAlreadyExistsException extends RuntimeException {
    public LongUrlAlreadyExistsException(String message) {
        super(message);
    }
}
