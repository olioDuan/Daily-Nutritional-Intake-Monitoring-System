package calculation;

import java.util.*;
import main.*;

public class NutritionCalculator {

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
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getCalories()).average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getProtein()).average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getCarbs()).average()
                        .orElse(0),
                days.stream().map(day -> day.getTotalNutrition()).mapToDouble(item -> item.getFat()).average()
                        .orElse(0));
    }
}
