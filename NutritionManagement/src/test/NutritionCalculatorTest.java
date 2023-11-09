package test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.lang.Math;

import org.junit.Test;

import main.NutritionValue;
import calculation.NutritionCalculator;

public class NutritionCalculatorTest {
    @Test
    public void accumulateNutritionValueEmpty() {
        ArrayList<NutritionValue> input = new ArrayList<>();
        NutritionValue output = NutritionCalculator.accumulateNutritionValue(input);
        NutritionValue expected = new NutritionValue(0, 0, 0, 0);
        assertEquals(expected.getCalories(), output.getCalories(), Math.ulp(1.0));
        assertEquals(expected.getProtein(), output.getProtein(), Math.ulp(1.0));
        assertEquals(expected.getCarbs(), output.getCarbs(), Math.ulp(1.0));
        assertEquals(expected.getFat(), output.getFat(), Math.ulp(1.0));
    }

    @Test
    public void accumulateNutritionValueTwo() {
        ArrayList<NutritionValue> input = new ArrayList<>();
        input.add(new NutritionValue(0.1, 0.3, 0.2, 0.4));
        input.add(new NutritionValue(0.5, 0.4, 0.3, 0.2));
        NutritionValue output = NutritionCalculator.accumulateNutritionValue(input);
        NutritionValue expected = new NutritionValue(0.6, 0.7, 0.5, 0.6);
        assertEquals(expected.getCalories(), output.getCalories(), Math.ulp(1.0));
        assertEquals(expected.getProtein(), output.getProtein(), Math.ulp(1.0));
        assertEquals(expected.getCarbs(), output.getCarbs(), Math.ulp(1.0));
        assertEquals(expected.getFat(), output.getFat(), Math.ulp(1.0));
    }
}
