package com.enceasy.Repository;

import com.enceasy.Entity.Enceasy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Enceasy, UUID> {

    boolean existsByurlEncurtada(String urlEncurtada);
    Optional findByurlEncurtada(String urlEncurtada);
    Optional findByurlOriginal(String urlEncurtada);
}
