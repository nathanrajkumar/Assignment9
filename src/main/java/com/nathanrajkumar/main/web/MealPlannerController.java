package com.nathanrajkumar.main.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nathanrajkumar.main.spoonacular.dto.Day;
import com.nathanrajkumar.main.spoonacular.dto.Week;
import com.nathanrajkumar.main.spoonacular.service.MealPlanService;

@RestController
public class MealPlannerController {
	
	@Autowired
	private MealPlanService mealPlanService;
	
	@GetMapping("/mealplanner/week")
	public ResponseEntity<Week> getWeekMeals(@RequestParam(required=false) String numCalories, @RequestParam(required=false) String diet, @RequestParam(required=false) String exclusions) {
		return mealPlanService.generateWeeklyMealPlan(numCalories, diet, exclusions);
	}
	
	@GetMapping("/mealplanner/day")
	public ResponseEntity<Day> getDayMeals(@RequestParam(required=false) String numCalories, @RequestParam(required=false) String diet, @RequestParam(required=false) String exclusions) {
		return mealPlanService.generateDayMealPlan(numCalories, diet, exclusions);
	}
}
