package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;
import calculation.NutritionCalculator;
import exceptions.InvalidDateException;

public class Day {
	public Day(String dateString) throws InvalidDateException {
		// WIP
		try {
			this.date = LocalDate.parse(dateString, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
			this.foodPortions = new ArrayList<>();
		} catch (DateTimeParseException e) {
			throw new InvalidDateException(String.format("Invalid date %s", dateString));
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
