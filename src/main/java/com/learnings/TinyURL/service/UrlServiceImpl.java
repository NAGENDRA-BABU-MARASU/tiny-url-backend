package com.learnings.TinyURL.service;

import com.learnings.TinyURL.entity.UrlEntity;
import com.learnings.TinyURL.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 11:25
 */
@Service
public class UrlServiceImpl implements UrlService{
    private final UrlRepository urlRepository;
    private static double counter;
    @Autowired
    StringRedisTemplate redisTemplate;
    String characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public UrlServiceImpl(UrlRepository urlRepository) {
        counter = new Random(System.currentTimeMillis()).nextDouble() * 10;
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlEntity saveUrl(String longUrl) {
        UrlEntity newUrlEntity = new UrlEntity();
        String shortUrl = this.urlShortener(longUrl);
        newUrlEntity.setLongUrl(longUrl);
        newUrlEntity.setShortUrl(shortUrl);
        urlRepository.save(newUrlEntity);
        System.out.println("url generated : " + shortUrl + " : " +  longUrl);
        redisTemplate.opsForValue().set(shortUrl, longUrl);
        return newUrlEntity;
    }

    @Override
    public UrlEntity getUrl(String shortUrl) {
        String longUrl = redisTemplate.opsForValue().get(shortUrl);
        if(longUrl != null ){
            UrlEntity urlEntity = new UrlEntity();
            urlEntity.setShortUrl(shortUrl);
            urlEntity.setLongUrl(longUrl);
            System.out.println("getting from redis");
            return urlEntity;
        }
        Optional<UrlEntity> urlEntityOptional = urlRepository.findById(shortUrl);
        try {
            System.out.println("getting from db");
            return urlEntityOptional.get();

        }
        catch (Exception e){
            System.out.println(e);
            return new UrlEntity();
        }
    }

    private String urlShortener(String url){
        // string to binary
        String urlWithCounter = (int)counter + url;
        counter += 1;
        String binaryString = convertToBinaryString(urlWithCounter);
        return binaryToBase62(binaryString);
    }

    public String convertToBinaryString(String input) {
        StringBuilder binaryString = new StringBuilder();

        for (char c : input.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryString.append(binaryChar);
        }

        return binaryString.toString();
    }

    public long binaryToDecimal(String binaryStr) {
        long decimal = 0;
        String reversedBinary = new StringBuilder(binaryStr.substring(0,54)).reverse().toString();

        for (int i = 0; i < reversedBinary.length(); i++) {
            char digit = reversedBinary.charAt(i);
            if (digit == '1') {
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    public String decimalToBase62(long decimalNum) {

        StringBuilder base62 = new StringBuilder();

        while (decimalNum > 0) {
            int remainder = (int) (decimalNum % 62);
            base62.insert(0, this.characterSet.charAt(remainder));
            decimalNum /= 62;
        }

        return base62.toString();
    }

    public String binaryToBase62(String binaryStr) {
        long decimalNum = binaryToDecimal(binaryStr);
        return decimalToBase62(decimalNum);
    }
}
