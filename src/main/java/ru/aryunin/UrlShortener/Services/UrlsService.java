package ru.aryunin.UrlShortener.Services;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aryunin.UrlShortener.Models.Url;
import ru.aryunin.UrlShortener.Repositories.UrlsRepository;
import ru.aryunin.UrlShortener.Utils.LongUrlAlreadyExistsException;
import ru.aryunin.UrlShortener.Utils.ShortUrlNotFoundException;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UrlsService {
    private final UrlsRepository urlsRepository;

    public UrlsService(UrlsRepository urlsRepository) {
        this.urlsRepository = urlsRepository;
    }

    /**
     * The method generates and adds a new Url object to the DB by long url
     * @param longUrl long url
     * @return short url
     */
    @Transactional
    public String addUrl(String longUrl) {
        if(urlsRepository.findByLongUrl(longUrl).isPresent())
            throw new LongUrlAlreadyExistsException("This long URL is already exists");
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortenUrl(longUrl));
        urlsRepository.save(url);
        return url.getShortUrl();
    }

    /**
     * The method generates a short url as the minimal part of the long url's sha256 hash
     * @param longUrl original url
     * @return short url
     */
    private String shortenUrl(String longUrl) {
        String fullHash = Hashing.sha256().hashString(longUrl, StandardCharsets.UTF_8).toString();
        StringBuilder partHash = new StringBuilder();
        partHash.append(fullHash.charAt(0));
        for(int i = 1; i < fullHash.length() && urlsRepository.findByShortUrl(partHash.toString()).isPresent(); i++)
            partHash.append(fullHash.charAt(i));
        return partHash.toString();
    }

    /**
     * The method returns a long url by the short url
     * @param shortUrl short url
     * @return long url
     */
    public String getLongUrl(String shortUrl) {
        Optional<Url> url = urlsRepository.findByShortUrl(shortUrl);
        if(url.isPresent()) return url.get().getLongUrl();
        else throw new ShortUrlNotFoundException("This short URL does not exist!");
    }
}
