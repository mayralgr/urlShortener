package com.urlShortener.urlShortener.controller;

import java.util.ArrayList;
import java.util.List;

import com.urlShortener.urlShortener.model.UrlInputForm;
import com.urlShortener.urlShortener.services.WebUrlShortenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    private WebUrlShortenerService webService;
    private List<UrlInputForm> urlsGenerated = new ArrayList<UrlInputForm>();
    
	@GetMapping("/{alias}")
	public ResponseEntity<String> retrieveUrlByAlias(@PathVariable String alias) {
        // search the url in the urlsGenerated
        UrlInputForm urlInList = webService.getUrlByAlias(urlsGenerated, alias);
        if(urlInList!=null)
        {
            return new ResponseEntity<>(urlInList.getUrl() ,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("The alias does not match any register" ,HttpStatus.BAD_REQUEST);
        }
	}

    /**
     * url : string from json post
     */
	@PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<String> generateAliasUrl(@RequestBody UrlInputForm url) {
        if(webService.validateUrl(url.getUrl())){
            // verification that does not exist in the list
            UrlInputForm urlInlist = webService.findExistinggUrl(urlsGenerated,url.getUrl());
            if(urlInlist==null)
            {
                // is a valid url, and does not exist in the list, an alias is generated
                webService.generateAlias(url);
                urlsGenerated.add(url);
                return new ResponseEntity<>("alias:"+ url.getAlias(),HttpStatus.OK);
            }
            else
            {
                // the url already exist in the list
                return new ResponseEntity<>("alias:"+ urlInlist.getAlias(),HttpStatus.OK);
            }
        }
        // url is not valid
        return new ResponseEntity<>("Invalid Url", HttpStatus.BAD_REQUEST);	
    }
    
    
}