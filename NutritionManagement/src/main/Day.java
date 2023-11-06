package main;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

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
		Stream<NutritionValue> nutritions = foodPortions.stream().map(foodPortion -> foodPortion.getNutrition());
		return new NutritionValue(
				nutritions.map(item -> item.getCalories()).reduce(0.0, (prev, curr) -> prev + curr),
				nutritions.map(item -> item.getProtein()).reduce(0.0, (prev, curr) -> prev + curr),
				nutritions.map(item -> item.getCarbs()).reduce(0.0, (prev, curr) -> prev + curr),
				nutritions.map(item -> item.getFat()).reduce(0.0, (prev, curr) -> prev + curr));
	}
}
