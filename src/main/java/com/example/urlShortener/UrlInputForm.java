package com.example.urlShortener;

import java.nio.charset.Charset;
import java.util.Random;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;

public class UrlInputForm {

	@NotNull
	private String url;
	private String alias ="";

	public UrlInputForm()
	{
	}

	public UrlInputForm(String url)
	{
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlias() {
		return this.alias;
	}

	public String toString() {
		return "Url: " + this.url + ", Alias:" + this.alias ;
	}

	public boolean validateUrl() {
		UrlValidator urlValid = new UrlValidator();
		return urlValid.isValid(this.url) || urlValid.isValid("http://" + this.url) || urlValid.isValid("https://" + this.url) ;
	}
    public void generateAlias() {
		if(this.isAGoogleUrl())
		{
			//  -   alias will have 5 characters length
			//  -   alias will use only alphanebic characters
			generateRandomAlias(5,1);
		}
		else
		{
			if(isAYahooUrl())
			{
				// -   alias will have 7 characters length
				//-   alias will use alphanumeric characters
				generateRandomAlias(7,2);
			}
			else{
				// Any other url
    			//-   alias will be based on the url itself by removing every special characters, vowels and numbers from it.
				generateRandomAlias(3);
			}
		}

	}

	private boolean isAGoogleUrl()
	{
		return this.url.contains("google");
	}

	private boolean isAYahooUrl()
	{
		return this.url.contains("yahoo");
	}

	// For google and yahoo 
	private String generateRandomAlias(int size, int type)
	{
		boolean useLetters;
		boolean useNumbers;
		switch(type)
		{
			case 1: // google
				useLetters = true;
				useNumbers = false;
				this.alias = RandomStringUtils.random(size, useLetters, useNumbers);
				break;
			case 2: // yahoo
				useLetters = true;
				useNumbers = true;
				this.alias = RandomStringUtils.random(size, useLetters, useNumbers);
				break;
		}
		return "";
	}

	// for all other urls
	private String generateRandomAlias(int type)
	{
		return "";
	}

}