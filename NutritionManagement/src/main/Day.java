package main;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import calculation.NutritionCalculator;

public class Day {
	public Day(String dateString) {
		// WIP
		try {
			this.date = LocalDate.parse(dateString);
			this.foodPortions = new ArrayList<>();
		} catch (Exception e) {

		}

	}

	private LocalDate date;
	private ArrayList<FoodPortion> foodPortions;

	public NutritionValue getTotalNutrition() {
		ArrayList<NutritionValue> nutritions = foodPortions.stream().map(foodPortion -> foodPortion.getNutrition())
				.collect(Collectors.toCollection(ArrayList::new));
		return NutritionCalculator.accumulateNutritionValue(nutritions);
	}
}
