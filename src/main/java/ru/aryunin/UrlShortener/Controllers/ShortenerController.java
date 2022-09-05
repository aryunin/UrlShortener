package ru.aryunin.UrlShortener.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.aryunin.UrlShortener.Services.UrlsService;

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
}
