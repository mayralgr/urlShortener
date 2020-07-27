package com.urlShortener.urlShortener.services.implementation;

import java.util.List;
import com.urlShortener.urlShortener.model.UrlInputForm;
import com.urlShortener.urlShortener.services.WebUrlShortenerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class WebUrlShortenerServiceImpl implements WebUrlShortenerService {

    @Override
    public void generateAlias(UrlInputForm url) {
        if (isAGoogleUrl(url.getUrl())) {
            // - alias will have 5 characters length
            // - alias will use only alphanebic characters
            url.setAlias(generateRandomAlias(5, 1));
        } else {
            if (isAYahooUrl(url.getUrl())) {
                // - alias will have 7 characters length
                // - alias will use alphanumeric characters
                url.setAlias(generateRandomAlias(7, 2));
            } else {
                // Any other url
                // - alias will be based on the url itself by removing every special characters,
                // vowels and numbers from it.
                String pattern = "[\\WaeiouAEIOU\\d]"; // patter for replace special characters, vowels and numbers
                String newAlias = url.getUrl().replaceAll(pattern, ""); 
		        url.setAlias(newAlias);  
            }
        }
    }

    @Override
    public UrlInputForm findExistingUrl(List<UrlInputForm> urlsGenerated, String url) {
        try { 
            return urlsGenerated.stream().filter(o -> o.getUrl().equals(url)).findFirst().get();
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UrlInputForm getUrlByAlias(List<UrlInputForm> urls, String alias) {
        try
        {
            UrlInputForm urlInList = urls.stream().filter(o -> o.getAlias().equals(alias)).findFirst().get();
            return urlInList;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    /**
     * Method that validates if the Url is validate
     * 
     */
    @Override
    public boolean validateUrl(String url) {
        UrlValidator urlValid = new UrlValidator();
        return urlValid.isValid(url) || urlValid.isValid("http://" + url) || urlValid.isValid("https://" + url);
    }

    /**
     * method that determines if the url contains the word google
     */
    private boolean isAGoogleUrl(String url) {
        return url.contains("google");
    }

    /**
     * method that determines if the url contains the word yahoo
     */
    private boolean isAYahooUrl(String url) {
        return url.contains("yahoo");
    }

    /**
     * Generation of randomAlias for google and yahoo urls
     */
    private String generateRandomAlias(int size, int type) {
        boolean useLetters;
        boolean useNumbers;
        String alias="";
        switch (type) {
            case 1: // google
                useLetters = true;
                useNumbers = false;
                alias = RandomStringUtils.random(size, useLetters, useNumbers);
				break;
			case 2: // yahoo
				useLetters = true;
				useNumbers = true;
				alias = RandomStringUtils.random(size, useLetters, useNumbers);
                break;
        }
        return alias;
    }
    
}