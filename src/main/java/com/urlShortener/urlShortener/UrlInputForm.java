package com.urlShortener.urlShortener;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;

public class UrlInputForm {

	private String url;
	private String alias ="";

	/**
	 * Constructors
	 */
	public UrlInputForm()
	{
	}

	public UrlInputForm(String url)
	{
		this.url = url;
	}

	/**
	 * Setters and getters
	 *
	 */
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

	/**
	 * Method that validates if the Url is validate
	 * 
	 */
	
	public boolean validateUrl() {
		UrlValidator urlValid = new UrlValidator();
		return urlValid.isValid(this.url) || urlValid.isValid("http://" + this.url) || urlValid.isValid("https://" + this.url) ;
	}

	/**
	 * It determinates wich method use to generate the alias based on the content of the URL
	 */
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
				generateRandomAlias();
			}
		}

	}

	/**
	 * method that determines if the url contains the word google
	 * */
	private boolean isAGoogleUrl()
	{
		return this.url.contains("google");
	}

	/**
	 * method that determines if the url contains the word yahoo
	 * */
	private boolean isAYahooUrl()
	{
		return this.url.contains("yahoo");
	}

	/**
	 * Generation of randomAlias for google and yahoo urls
	 * */
	private void generateRandomAlias(int size, int type)
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
	}

	/**
	 * Alias generation for urls that not contain google or yahoo
	 * */
	private void generateRandomAlias()
	{
		String pattern = "[\\WaeiouAEIOU\\d]"; // patter for replace special characters, vowels and numbers
		this.alias = this.url.replaceAll(pattern, ""); 
	}

}