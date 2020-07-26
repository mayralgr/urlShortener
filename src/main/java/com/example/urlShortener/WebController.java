package com.example.urlShortener;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.ModelAndView;
@Controller
public class WebController implements WebMvcConfigurer {
    List<UrlInputForm> urlsGenerated = new ArrayList<UrlInputForm>();
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public ModelAndView showForm(UrlInputForm url) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("url", "hola");
        mv.setViewName("form");
        return mv;
	}

    /**
     * Where 
     * url : string for json post
     * 
     */
	@PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity<String> generateAliasUrl(@RequestBody UrlInputForm url) {
       // System.out.println(url);
        System.out.println(url.getUrl());
        System.out.println(url.validateUrl());
        if(url.validateUrl()){
            // is a valid url, an alias is generated
            url.generateAlias();
            System.out.println(url.getAlias());
            // verification that does not exist in the list
            if(!urlsGenerated.stream().anyMatch(source -> source.getUrl() == url.getUrl()))
            {
                urlsGenerated.add(url);
                System.out.println("success");
                System.out.println(urlsGenerated.toString());
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
    }
    
}