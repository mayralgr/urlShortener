package com.example.urlShortener;

// import javax.validation.Valid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;*/

//@RestController
@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	/*@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}

	@GetMapping("/google")
	public String google(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Google it %s!", name);
	}

	@GetMapping("/yahoo")
	public String yahoo(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("yahoo it %s!", name);
	}*/

}
