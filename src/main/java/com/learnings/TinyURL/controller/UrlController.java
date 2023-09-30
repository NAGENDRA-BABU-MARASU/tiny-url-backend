package com.learnings.TinyURL.controller;

import com.learnings.TinyURL.dto.UrlDTO;
import com.learnings.TinyURL.entity.UrlEntity;
import com.learnings.TinyURL.service.UrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 14:20
 */
@RestController
@RequestMapping("/tiny-url")
@CrossOrigin(origins = "http://localhost:3000")
public class UrlController {
    private final UrlService urlService;
    private final String baseUrl;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
        this.baseUrl = "http://localhost:8080/tiny-url/";
    }

    @PostMapping
    public ResponseEntity<UrlDTO> saveUrl(@RequestBody UrlDTO urlDTO){
        UrlEntity savedURLEntity = urlService.saveUrl(urlDTO.getLongUrl());
        // mapping
        UrlDTO savedUrlDto = new UrlDTO();
        savedUrlDto.setShortUrl(this.baseUrl + savedURLEntity.getShortUrl());
        savedUrlDto.setLongUrl(savedURLEntity.getLongUrl());
        return new ResponseEntity<>(savedUrlDto, HttpStatus.OK);
    }


    @GetMapping("{shortUrl}")
    public ResponseEntity<UrlDTO> getUrl(@PathVariable("shortUrl") String shortUrl){
        UrlDTO urlDTO = new UrlDTO();
        UrlEntity urlEntity = urlService.getUrl(shortUrl);
//        System.out.println(urlEntity);
        urlDTO.setShortUrl(urlEntity.getShortUrl());
        urlDTO.setLongUrl(urlEntity.getLongUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlEntity.getLongUrl()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
