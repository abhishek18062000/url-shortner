package com.example.urlshortner.controller;
import com.example.urlshortner.dto.UrlRequest;
import com.example.urlshortner.service.UrlShortnerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class UrlController {
    private final UrlShortnerService urlShortner;

    public UrlController(UrlShortnerService urlShortner) {
        this.urlShortner = urlShortner;
    }

    @PostMapping("/shortner")
    public String shortener(@RequestBody UrlRequest request){
        return urlShortner.shortnerService(request.getLongUrl());
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        String longUrl = urlShortner.returnLongUrl(shortCode);
        return new RedirectView(longUrl);
    }
}
