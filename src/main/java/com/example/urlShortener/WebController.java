package com.example.urlShortener;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
	public ModelAndView showForm(UrlInputForm urlForm) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("url", "hola");
        mv.setViewName("form");
        return mv;
	}

    /**
     * Where 
     * url : string for json post
     * urlForm: url from form interface
     * 
     */
	@PostMapping("/")
    public ModelAndView checkUrl(// @RequestBody String url, 
                                @Valid UrlInputForm urlForm, 
                                Model model, 
                                BindingResult bindingResult) {
        // if it comes from post and not the interface, the object is set

        /*System.out.println(url);
        if (urlForm.getUrl() == null)
        {
            urlForm = new UrlInputForm();
            urlForm.setUrl(url);
        }*/

		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("form");
            mv.addObject("url", urlForm.getUrl());
            mv.setViewName("form");
            return mv;
        }
        
        System.out.println(urlForm.getUrl());
        System.out.println(urlForm.validateUrl());
        if(urlForm.validateUrl()){
            // is a valid url, an alias is generated
            urlForm.generateAlias();
            System.out.println(urlForm.getAlias());
            // verification that does not exist in the list
            if(!urlsGenerated.stream().anyMatch(source -> source.getUrl() == urlForm.getUrl()))
            {
                urlsGenerated.add(urlForm);
                System.out.println("success");
                System.out.println(urlsGenerated.toString());
            }
        }
        ModelAndView mv = new ModelAndView("results");
        mv.addObject("url", urlForm.getUrl());
        mv.setViewName("results");
        return mv;
    }
    
}