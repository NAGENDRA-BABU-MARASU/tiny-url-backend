package com.learnings.TinyURL.repository;

/*
 * @author: Nagendra
 * version: 1.0.0
 * @created: 29/09/2023 - 11:19
 */

import com.learnings.TinyURL.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {
}
