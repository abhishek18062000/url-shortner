package com.example.urlshortner.service;

import com.example.urlshortner.entity.Url;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShortnerService {
    private final Map<String, String> urlMap = new HashMap<>();
    private UrlRepository urlRepository;

    public UrlShortnerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortnerService(String longUrl){
        String encoded = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(longUrl.getBytes());
        //urlMap.put(encoded,longUrl);
        Url url = new Url(encoded, longUrl);
        urlRepository.save(url);
        return encoded;
    }

    public String returnLongUrl(String encoded){
        Url url = urlRepository.findByShortCode(encoded)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        return url.getOriginalUrl();
    }
}
