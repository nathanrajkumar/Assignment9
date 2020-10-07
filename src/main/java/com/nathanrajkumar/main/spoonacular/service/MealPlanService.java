package com.nathanrajkumar.main.spoonacular.service;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nathanrajkumar.main.spoonacular.Spoonacular;
import com.nathanrajkumar.main.spoonacular.dto.Day;
import com.nathanrajkumar.main.spoonacular.dto.Week;

@Service
public class MealPlanService {

	private Spoonacular spoonacular;

	@Autowired
	private RestTemplate restTemplate;
	@Value("${spoonacular.urls.base}")
	private String baseUrl;
	@Value("${spoonacular.urls.mealplan}")
	private String mealPlanUrl;
	@Value("${spoonacular.authentication.apikey}")
	private String apiKey;

	public ResponseEntity<Week> generateWeeklyMealPlan(String numCalories, String diet, String exclusions) {
		spoonacular = new Spoonacular(numCalories, diet, exclusions);
		spoonacular.setUri(generateURI("week"));
		return restTemplate.exchange(spoonacular.getUri(), HttpMethod.GET, createHttpEntity(), Week.class);
	}

	public ResponseEntity<Day> generateDayMealPlan(String numCalories, String diet, String exclusions) {
		spoonacular = new Spoonacular(numCalories, diet, exclusions);
		spoonacular.setUri(generateURI("day"));
		return restTemplate.exchange(spoonacular.getUri(), HttpMethod.GET, createHttpEntity(), Day.class);
	}

	private HttpEntity<String> createHttpEntity() {
		return new HttpEntity<String>(spoonacular.getUri().getRawPath(), generateHeaders());
	}

	private HttpHeaders generateHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return headers;
	}

	private URI generateURI(String timeframe) {		
		return UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlanUrl)
		   .queryParams(createParameterMap(timeframe))
		   .build()
		   .toUri();
	}

	private MultiValueMap<String, String> createParameterMap(String timeframe) {
		MultiValueMap<String, String> mvmParams = new LinkedMultiValueMap<String, String>();
		String numCalories = spoonacular.getNumCalories();
		String diet = spoonacular.getDiet();
		String exclusions = spoonacular.getExclusions();

		mvmParams.add("apiKey", apiKey);
		mvmParams.add("timeFrame", timeframe);
		if (numCalories != null)
			mvmParams.add("targetCalories", numCalories);
		if (diet != null)
			mvmParams.add("diet", diet);
		if (exclusions != null)
			mvmParams.add("exclude", exclusions);

		return mvmParams;
	}
}
