package com.learnings.TinyURL;

import org.aspectj.weaver.tools.cache.SimpleCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@SpringBootApplication
@EnableCaching
public class TinyUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyUrlApplication.class, args);

	}

//	@Bean
//	public CacheManager cacheManager(){
//		SimpleCacheManager cacheManager = new SimpleCacheManager();
//		cacheManager.setCaches(Set.of(new ConcurrentMapCache("UrlEntities")));
//		return cacheManager;
//	}

}
