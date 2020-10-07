package com.nathanrajkumar.main.spoonacular;

import java.net.URI;

public class Spoonacular {

	private URI uri;
	private String numCalories;
	private String diet;
	private String exclusions;

	public Spoonacular(String numCalories, String diet, String exclusions) {
		this.numCalories = numCalories;
		this.diet = diet;
		this.exclusions = exclusions;
	}

	public String getNumCalories() {
		return numCalories;
	}

	public void setNumCalories(String numCalories) {
		this.numCalories = numCalories;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getExclusions() {
		return exclusions;
	}

	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	

}
