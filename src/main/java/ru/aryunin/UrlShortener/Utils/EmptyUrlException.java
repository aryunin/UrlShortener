package ru.aryunin.UrlShortener.Utils;

public class EmptyUrlException extends RuntimeException {
    public EmptyUrlException(String message) {
        super(message);
    }
}
