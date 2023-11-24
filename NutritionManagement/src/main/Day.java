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
            this.date = LocalDate.parse(dateString);
            this.foodPortions = new ArrayList<>();
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getMessage());
        }

    }

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    private ArrayList<FoodPortion> foodPortions;

    public NutritionValue getTotalNutrition() {
        ArrayList<NutritionValue> nutritions = foodPortions.stream().map(foodPortion -> foodPortion.getNutrition())
                .collect(Collectors.toCollection(ArrayList::new));
        return NutritionCalculator.accumulateNutritionValue(nutritions);
    }

    /**
     * Add food portion if the food type is new, else update
     * 
     * @param food
     * @param quantity
     */
    public void upsertFoodPortion(Food food, double quantity) {
        FoodPortion searchResult = this.foodPortions.stream()
                .filter(foodPortion -> foodPortion.getName().equals(food.getName())).findFirst().orElse(null);
        if (searchResult == null) {
            this.foodPortions.add(new FoodPortion(food, quantity));
        } else {
            searchResult.setQuantity(quantity);
        }
    }

    public void deleteFoodPortion(FoodPortion foodPortion) {
        this.foodPortions.remove(foodPortion);
    }

    public void deleteFoodPortion(String name) {
        FoodPortion searchResult = this.foodPortions.stream()
                .filter(foodPortion -> foodPortion.getName().equals(name)).findFirst().orElse(null);
        if (searchResult != null) {
            this.foodPortions.remove(searchResult);
        }
    }

    public void listFoodPortions() {
        this.foodPortions.stream().forEach(portion -> System.out.println(portion.toString()));
    }
}
