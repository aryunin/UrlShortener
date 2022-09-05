package ru.aryunin.UrlShortener.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aryunin.UrlShortener.Services.UrlsService;
import ru.aryunin.UrlShortener.Utils.ShortUrlNotFoundException;

@RestController
public class ShortenerController {
    private final UrlsService urlsService;

    public ShortenerController(UrlsService urlsService) {
        this.urlsService = urlsService;
    }

    @PostMapping
    public String addUrl(@RequestBody String longUrl) {
        return urlsService.addUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    @ResponseBody
    public String getLongUrl(@PathVariable String shortUrl) {
        return urlsService.getLongUrl(shortUrl);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ShortUrlNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
