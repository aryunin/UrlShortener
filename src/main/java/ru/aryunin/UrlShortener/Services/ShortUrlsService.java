package ru.aryunin.UrlShortener.Services;

import org.springframework.stereotype.Service;
import ru.aryunin.UrlShortener.Repositories.ShortUrlsRepository;

@Service
public class ShortUrlsService {
    private final ShortUrlsRepository shortUrlsRepository;

    public ShortUrlsService(ShortUrlsRepository shortUrlsRepository) {
        this.shortUrlsRepository = shortUrlsRepository;
    }
}
