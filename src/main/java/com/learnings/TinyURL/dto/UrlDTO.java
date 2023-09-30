package com.learnings.TinyURL.dto;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 14:32
 */

public class UrlDTO {
    private String shortUrl;
    private String longUrl;

    public UrlDTO(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public UrlDTO() {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
