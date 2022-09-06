package ru.aryunin.UrlShortener.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Url implements Serializable {
    @Id
    @Column(name = "long_url")
    private String longUrl;
    @Column(name = "short_url")
    private String shortUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
