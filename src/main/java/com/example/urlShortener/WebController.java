package com.example.urlShortener;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.ModelAndView;
@Controller
public class WebController implements WebMvcConfigurer {

    List<Url> urlsGenerated = new ArrayList<Url>();
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public ModelAndView showForm(Url url) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("url", "hola");
        mv.setViewName("form");
        return mv;
	}

	@PostMapping("/")
	public ModelAndView checkUrl(@Valid Url url, Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("form");
            mv.addObject("url", url.getUrl());
            mv.setViewName("form");
            return mv;
        }
        System.out.println(url.validateUrl());
        if(url.validateUrl()){
            // is a valid url, an alias is generated
            url.generateAlias();
            System.out.println(url.getAlias());
            urlsGenerated.add(url);
            
        }
        ModelAndView mv = new ModelAndView("results");
        mv.addObject("url", url.getUrl());
        mv.setViewName("results");
        return mv;
    }
    
}