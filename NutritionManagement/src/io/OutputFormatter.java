package io;

import calculation.NutritionCalculator;
import main.NutritionValue;

public class OutputFormatter {
    public static void printTotalNutritionValue(NutritionValue nutritionValue) {
        NutritionValue percentageValue = NutritionCalculator.computePercentage(nutritionValue);
        System.out.println("Total Nutrition Value:");
        System.out.println("----------------------");
        System.out.println("Calories: " + formatDouble(nutritionValue.getCalories()) + " kcal, "
                + formatPercentage(percentageValue.getCalories()));
        System.out.println("Protein: " + formatDouble(nutritionValue.getProtein()) + " g, "
                + formatPercentage(percentageValue.getProtein()));
        System.out.println("Carbs: " + formatDouble(nutritionValue.getCarbs()) + " g, "
                + formatPercentage(percentageValue.getCarbs()));
        System.out.println("Fat: " + formatDouble(nutritionValue.getFat()) + " g, "
                + formatPercentage(percentageValue.getFat()));
        System.out.println();
    }

    public static void printAverageNutritionValue(NutritionValue nutritionValue) {
        System.out.println("Average Nutrition Value per Day:");
        System.out.println("--------------------------------");
        System.out.println("Calories: " + formatDouble(nutritionValue.getCalories()) + " kcal");
        System.out.println("Protein: " + formatDouble(nutritionValue.getProtein()) + " g");
        System.out.println("Carbs: " + formatDouble(nutritionValue.getCarbs()) + " g");
        System.out.println("Fat: " + formatDouble(nutritionValue.getFat()) + " g");
        System.out.println();
    }

    private static String formatDouble(double value) {
        return String.format("%.2f", value);
    }

    private static String formatPercentage(double value) {
        return String.format("%.2f%%", value * 100);
    }
}