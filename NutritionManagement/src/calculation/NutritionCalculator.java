package calculation;

import java.util.*;
import java.util.stream.Stream;

import main.*;

public class NutritionCalculator {

    /**
     * Accumulate an ArrayList of NutritionValue to get total.
     * 
     * @return a new NutritionValue object representing the total Nutrition Value
     */
    public static NutritionValue accumulateNutritionValue(ArrayList<NutritionValue> nutritionValues) {
        Stream<NutritionValue> nutritions = nutritionValues.stream();
        return new NutritionValue(
                nutritions.mapToDouble(item -> item.getCalories()).sum(),
                nutritions.mapToDouble(item -> item.getProtein()).sum(),
                nutritions.mapToDouble(item -> item.getCarbs()).sum(),
                nutritions.mapToDouble(item -> item.getFat()).sum());
    }

    /**
     * 
     * @param days
     * @return a new NutritionValue object representing the average Nutrition Value per day
     */
    public static NutritionValue computeAverage(ArrayList<Day> days) {
        Stream<NutritionValue> nutritions = days.stream().map(day -> day.getTotalNutrition());
        return new NutritionValue(
                nutritions.mapToDouble(item -> item.getCalories()).average().orElse(0),
                nutritions.mapToDouble(item -> item.getProtein()).average().orElse(0),
                nutritions.mapToDouble(item -> item.getCarbs()).average().orElse(0),
                nutritions.mapToDouble(item -> item.getFat()).average().orElse(0));
    }
}
