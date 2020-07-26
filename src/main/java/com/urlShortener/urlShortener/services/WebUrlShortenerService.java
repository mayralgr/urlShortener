package com.urlShortener.urlShortener.services;

import java.util.List;

import com.urlShortener.urlShortener.model.UrlInputForm;
public interface WebUrlShortenerService {
    public void generateAlias(UrlInputForm url);
    public UrlInputForm findExistinggUrl(List<UrlInputForm> urls, String newUrl);
    public UrlInputForm getUrlByAlias(List<UrlInputForm> urls, String alias);
    public boolean validateUrl(String url);
}