package com.learnings.TinyURL.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 11:12
 */
@Entity
public class UrlEntity implements Serializable {

    @Id
    private String shortUrl;
    private String longUrl;

    public static final String HASH_KEY = "UrlEntity";

    public UrlEntity(){
        this.longUrl = "";
        this.shortUrl = "";
    }

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

    @Override
    public String toString() {
        return "UrlEntity{" +
                "shortUrl='" + shortUrl + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
}
