package com.urlShortener.urlShortener.model;

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
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return "Url: " + this.url + ", Alias:" + this.alias ;
	}

}