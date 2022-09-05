package ru.aryunin.UrlShortener.Services;

import com.google.common.hash.Hashing;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aryunin.UrlShortener.Models.Url;
import ru.aryunin.UrlShortener.Repositories.UrlsRepository;

import java.nio.charset.StandardCharsets;

@Service
@Transactional(readOnly = true)
public class UrlsService {
    private final UrlsRepository urlsRepository;

    public UrlsService(UrlsRepository urlsRepository) {
        this.urlsRepository = urlsRepository;
    }

    @Transactional
    public String addUrl(String longUrl) {
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortenUrl(longUrl));
        urlsRepository.save(url);
        return url.getShortUrl();
    }

    private String shortenUrl(String longUrl) {
        String fullHash = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();
        StringBuilder partHash = new StringBuilder("");
        partHash.append(fullHash.charAt(0));
        for(int i = 1; i < fullHash.length() && urlsRepository.findByShortUrl(partHash.toString()).isPresent(); i++)
            partHash.append(fullHash.charAt(i));
        return partHash.toString();
    }

    public String getLongUrl(String shortUrl) {
        return urlsRepository.findByShortUrl(shortUrl).get().getLongUrl(); // TODO
    }
}