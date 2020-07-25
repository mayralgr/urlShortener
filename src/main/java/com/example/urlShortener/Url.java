package com.example.urlShortener;

import javax.validation.constraints.NotNull;

import org.apache.commons.validator.routines.UrlValidator;

public class Url {

	@NotNull
	private String url;
	private String alias;

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
		return "Url: " + this.url + ")";
	}

	public boolean validateUrl() {
		UrlValidator urlValid = new UrlValidator();
		return urlValid.isValid(this.url) || urlValid.isValid("http://" + this.url) || urlValid.isValid("https://" + this.url) ;
	}
    public void generateAlias() {
		this.alias = "";
	}
}