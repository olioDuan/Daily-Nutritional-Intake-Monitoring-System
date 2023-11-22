package calculation;

import java.util.*;
import main.*;

public class NutritionCalculator {

    /**
     * (2000, 50, 275, 78)
     * as in
     * https://www.fda.gov/food/nutrition-facts-label/daily-value-nutrition-and-supplement-facts-labels
     */
    private static NutritionValue referenceNutritionValue = new NutritionValue(2000, 50, 275, 78);

    /**
     * Accumulate an ArrayList of NutritionValue to get total.
     * 
     * @return a new NutritionValue object representing the total Nutrition Value
     */
    public static NutritionValue accumulateNutritionValue(ArrayList<NutritionValue> nutritionValues) {
        return new NutritionValue(
                nutritionValues.stream().mapToDouble(item -> item.getCalories()).sum(),
                nutritionValues.stream().mapToDouble(item -> item.getProtein()).sum(),
                nutritionValues.stream().mapToDouble(item -> item.getCarbs()).sum(),
                nutritionValues.stream().mapToDouble(item -> item.getFat()).sum());
    }

    /**
     * 
     * @param days
     * @return a new NutritionValue object representing the average Nutrition Value
     *         per day
     */
    public static NutritionValue computeAverage(ArrayList<Day> days) {
        return new NutritionValue(
                days.stream().map(day -> day.getTotalNutrition())
                        .mapToDouble(item -> item.getCalories())
                        .average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getProtein())
                        .average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getCarbs())
                        .average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getFat())
                        .average()
                        .orElse(0));
    }

    /**
     * Input total NutritionValue of a Day and returns a new NutritionValue object,
     * each field is corresponding to the percentage of the field in recommended
     * NutritionValue,
     * represented in double (0.12 => 12%)
     * 
     * @param nutritionValue
     * @return
     */
    public static NutritionValue computePercentage(NutritionValue nutritionValue) {
        return new NutritionValue(
                nutritionValue.getCalories() / NutritionCalculator.referenceNutritionValue.getCalories(),
                nutritionValue.getProtein() / NutritionCalculator.referenceNutritionValue.getProtein(),
                nutritionValue.getCarbs() / NutritionCalculator.referenceNutritionValue.getCarbs(),
                nutritionValue.getFat() / NutritionCalculator.referenceNutritionValue.getFat());
    }

    /**
     * Input a Day, accumulate total nutrition automatically and returns the
     * percentages in recommended values,
     * as double numbers (0.12 => 12%)
     * 
     * @param day
     * @return
     */
    public static NutritionValue computePercentage(Day day) {
        NutritionValue nutritionValue = day.getTotalNutrition();
        return NutritionCalculator.computePercentage(nutritionValue);
    }
}
