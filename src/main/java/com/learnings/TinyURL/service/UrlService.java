package com.learnings.TinyURL.service;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 11:22
 */

import com.learnings.TinyURL.entity.UrlEntity;

public interface UrlService {
    // save url
    UrlEntity saveUrl(String longUrl);
    UrlEntity getUrl(String shortUrl);
}
