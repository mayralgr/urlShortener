package com.example.urlShortener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {
    List<UrlInputForm> urlsGenerated = new ArrayList<UrlInputForm>();
    
	@GetMapping(path = "/{url}")
	public void retrieveUrlByAlias(@PathVariable String url) {
        System.out.println(url);
	}

    /**
     * url : string from json post
     */
	@PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<String> generateAliasUrl(@RequestBody UrlInputForm url) {
       // System.out.println(url);
        System.out.println(url.getUrl());
        if(url.validateUrl()){
            // verification that does not exist in the list
            // it filters the list and compares the url with the incoming url, if does not exists, it appends it to the list
            if( ! urlsGenerated.stream().filter(o -> o.getUrl().equals(url.getUrl())).findFirst().isPresent())
            {
                // is a valid url, and does not exist in the list, an alias is generated
                url.generateAlias();
                System.out.println(url.getAlias());
                urlsGenerated.add(url);
                System.out.println(urlsGenerated.toString());
                return new ResponseEntity("alias:"+ url.getAlias(),HttpStatus.OK);
            }
            else
            {
                UrlInputForm urlInList = urlsGenerated.stream().filter(o -> o.getUrl().equals(url.getUrl())).findFirst().get();
                return new ResponseEntity("alias:"+ urlInList.getAlias(),HttpStatus.OK);
            }
        }
        return new ResponseEntity("Invalid Url", HttpStatus.BAD_REQUEST);	
    }
    
}