package ru.aryunin.UrlShortener.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aryunin.UrlShortener.Models.Url;

import java.util.Optional;

@Repository
public interface UrlsRepository extends JpaRepository<Url, String> {
    Optional<Url> findByShortUrl(String shortUrl);
}
