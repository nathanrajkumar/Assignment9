package com.nathanrajkumar.main;

import java.net.URI;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathanrajkumar.main.spoonacular.dto.Day;
import com.nathanrajkumar.main.spoonacular.dto.Week;

@SpringBootTest
class Assignment9ApplicationTests {

	@Value("${spoonacular.urls.base}")
	private String baseUrl;
	@Value("${spoonacular.urls.mealplan}")
	private String mealPlanUrl;
	@Value("${spoonacular.authentication.apikey}")
	private String apiKey;
	
	@Test
	public void testSpoonacularGetAndWeekResponseInit() throws JsonProcessingException {

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlanUrl)
				.queryParam("apiKey", apiKey)
				.queryParam("timeFrame", "week")
				.queryParam("targetCalories", 1500)
				.queryParam("diet", "Ketogenic")
				.build()
				.toUri();

		System.out.println(uri.getRawQuery());
		HttpEntity<String> entity = new HttpEntity<String>(uri.getRawPath(), headers);
		ResponseEntity<Week> response = rt.exchange(uri, HttpMethod.GET, entity, Week.class);

		System.out.println(response.getBody());
		System.out.println(response.getBody().getDaysOfTheWeek().toString());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(response.getBody()));
	}

	@Test
	public void testSpoonacularGetAndDayResponseInit() throws JsonProcessingException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlanUrl)
				.queryParam("apiKey", apiKey)
				.queryParam("timeFrame", "day")
				.queryParam("targetCalories", 1500)
				.queryParam("diet", "Ketogenic")
				.build()
				.toUri();
		// parameters and header
		HttpEntity<String> entity = new HttpEntity<String>(uri.getRawPath(), headers);
		ResponseEntity<Day> response = rt.exchange(uri, HttpMethod.GET, entity, Day.class);

		System.out.println(response.getBody());
		System.out.println(response.getBody().getMeals());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(response.getBody()));
	}

}
