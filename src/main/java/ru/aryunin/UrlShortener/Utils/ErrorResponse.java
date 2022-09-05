package ru.aryunin.UrlShortener.Utils;

public class ErrorResponse {
    final private String message;
    final private long timestamp;

    public ErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
