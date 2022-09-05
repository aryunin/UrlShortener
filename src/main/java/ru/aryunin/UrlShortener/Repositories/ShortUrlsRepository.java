package ru.aryunin.UrlShortener.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aryunin.UrlShortener.Models.Url;

@Repository
public interface ShortUrlsRepository extends JpaRepository<Url, String> {
}
