package com.nathanrajkumar.main.spoonacular.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Week {

	@JsonProperty("week")
	private DaysOfTheWeek daysOfTheWeek;

	public DaysOfTheWeek getDaysOfTheWeek() {
		return daysOfTheWeek;
	}

	public void setDaysOfTheWeek(DaysOfTheWeek daysOfTheWeek) {
		this.daysOfTheWeek = daysOfTheWeek;
	}

}
