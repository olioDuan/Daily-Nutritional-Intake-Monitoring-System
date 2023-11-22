package io;

import main.NutritionValue;

public class OutputFormatter {
    public static void printTotalNutritionValue(NutritionValue nutritionValue) {
        System.out.println("Total Nutrition Value:");
        System.out.println("----------------------");
        System.out.println("Calories: " + formatDouble(nutritionValue.getCalories()) + " kcal");
        System.out.println("Protein: " + formatDouble(nutritionValue.getProtein()) + " g");
        System.out.println("Carbs: " + formatDouble(nutritionValue.getCarbs()) + " g");
        System.out.println("Fat: " + formatDouble(nutritionValue.getFat()) + " g");
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
}