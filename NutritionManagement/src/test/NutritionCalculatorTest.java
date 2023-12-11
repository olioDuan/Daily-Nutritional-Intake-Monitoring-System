package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.Math;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.*;
import main.Day;
import main.Food;
import main.FoodPortion;
import main.Main;
import main.NutritionValue;
import main.User;
import calculation.NutritionCalculator;
import calculation.RecommendationSystem;
import exceptions.DayNotSelectedException;
import exceptions.InsufficientArgumentsException;
import exceptions.InvalidDateException;
import exceptions.ResourceNotFoundException;
import io.InputParser;

public class NutritionCalculatorTest {
    @Test
    public void Test1_accumulateNutritionValueEmpty() {
        ArrayList<NutritionValue> input = new ArrayList<>();
        NutritionValue output = NutritionCalculator.accumulateNutritionValue(input);
        NutritionValue expected = new NutritionValue(0, 0, 0, 0);
        assertEquals(expected.getCalories(), output.getCalories(), Math.ulp(1.0));
        assertEquals(expected.getProtein(), output.getProtein(), Math.ulp(1.0));
        assertEquals(expected.getCarbs(), output.getCarbs(), Math.ulp(1.0));
        assertEquals(expected.getFat(), output.getFat(), Math.ulp(1.0));
    }

    @Test
    public void Test2_accumulateNutritionValueTwo() {
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
    
    @Test
    public void Test3_unitTestNutritionValue_1(){
    	double cal = 10.5;
        double protein = 100.2;
        double carb = 1000.4;
        double fat = 1.8;
        NutritionValue res = new NutritionValue(cal, protein, carb, fat);
        
        String result = res.toString();
        String expected = "Calories: 10.50, Protein: 100.20, Carbs: 1000.40, Fat: 1.80";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test4_unitTestNutritionValue_2(){
    	double cal = 10;
        double protein = 100;
        double carb = 1000;
        double fat = 1;
        NutritionValue res = new NutritionValue(cal, protein, carb, fat);
        
        String result = res.toString();
        String expected = "Calories: 10.00, Protein: 100.00, Carbs: 1000.00, Fat: 1.00";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test5_unitTestNutritionValue_3(){
    	double cal = 10.55;
        double protein = 100.134;
        double carb = 1000.43;
        double fat = 1.66;
        NutritionValue res = new NutritionValue(cal, protein, carb, fat);
        
        String result = res.toString();
        String expected = "Calories: 10.55, Protein: 100.13, Carbs: 1000.43, Fat: 1.66";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test6_unitTestFood_1(){
    	String name = "Burger";
    	double cal = 10.5;
        double protein = 100.2;
        double carb = 1000.4;
        double fat = 1.8;
        Food food = new Food(name, cal, protein, carb, fat);
        
        String result = food.getNutritionValue().toString();
        String expected = "Calories: 10.50, Protein: 100.20, Carbs: 1000.40, Fat: 1.80";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test7_unitTestFood_2(){
    	String name = "Burger";
    	double cal = 10.5;
        double protein = 100.2;
        double carb = 1000.4;
        double fat = 1.8;
        Food food = new Food(name, cal, protein, carb, fat);
        
        String result = food.getName();
        String expected = "Burger";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test8_unitTestFood_3(){
    	String name = "Burger";
    	double cal = 10.5;
        double protein = 100.2;
        double carb = 1000.4;
        double fat = 1.8;
        Food food = new Food(name, cal, protein, carb, fat);
        
        String result = food.toString();
        String expected = "Name: Burger, Nutrition Values: (Calories: 10.50, Protein: 100.20, Carbs: 1000.40, Fat: 1.80)";
        
        assertEquals(result, expected);
    }
    
    @Test
    public void Test9_unitTestInputParser_1(){
    	// File path may be vary, in this case the file location is in my local machine. Please change the value of filepath if necessary
    	String filepath = "D:\\Daily-Nutritional-Intake-Monitoring-System-main\\foodtypes.txt";
    	ArrayList<Food> foods = InputParser.parseInputFoodFile(filepath);
    	
    	assertEquals(foods.size(), 1);
    }
    
    @Test
    public void Test10_unitTestInputParser_2(){
    	// File path may be vary, in this case the file location is in my local machine. Please change the value of filepath if necessary
    	String filepath = "D:\\Daily-Nutritional-Intake-Monitoring-System-main\\foodtypes2.txt";
    	ArrayList<Food> foods = InputParser.parseInputFoodFile(filepath);
    	
    	assertEquals(foods.size(), 0);
    }
    
    @Test
    public void Test11_unitTestInputParser_3(){
    	// File path may be vary, in this case the file location is in my local machine. Please change the value of filepath if necessary
    	String filepath = "D:\\Daily-Nutritional-Intake-Monitoring-System-main\\foodtypes3.txt";
    	ArrayList<Food> foods = InputParser.parseInputFoodFile(filepath);
    	
    	assertEquals(foods.size(), 2);
    }
    
    @Test
    public void Test12_TestRecommendationSystem_1() {
    	double cal = 1300;
    	double protein = 30;
    	double carb = 250;
    	double fat = 40;
    	NutritionValue nutri = new NutritionValue(cal,protein,carb,fat);
    	
    	String rec = RecommendationSystem.generateRecommendation(nutri);
    	assertEquals(rec, "Based on your nutrition intake, here are some recommendations:\n\nReduce calorie intake.\nReduce protein intake.\nReduce carbohydrate intake.\nReduce fat intake.\n");
    	
    }
    
    @Test
    public void Test13_TestRecommendationSystem_2() {
    	double cal = 1000;
    	double protein = 5;
    	double carb = 100;
    	double fat = 10;
    	NutritionValue nutri = new NutritionValue(cal,protein,carb,fat);
    	
    	String rec = RecommendationSystem.generateRecommendation(nutri);
    	assertEquals(rec, "Based on your nutrition intake, here are some recommendations:\n\nIncrease calorie intake.\nIncrease protein intake.\nIncrease carbohydrate intake.\nIncrease fat intake.\n");
    	
    }
    
    @Test
    public void Test14_integrationTestRound1_1() {
    	String name = "Fish";
    	double cal = 1000;
    	double protein = 5;
    	double carb = 100;
    	double fat = 10;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 2;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	NutritionValue res = foods.getNutrition();
    	String result = res.toString();
    	String expected = "Calories: 2000.00, Protein: 10.00, Carbs: 200.00, Fat: 20.00";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test15_integrationTestRound1_2() {
    	String name = "Fish";
    	double cal = 1000.3;
    	double protein = 5.2;
    	double carb = 100.8;
    	double fat = 10.6;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 4;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	NutritionValue res = foods.getNutrition();
    	String result = res.toString();
    	String expected = "Calories: 4001.20, Protein: 20.80, Carbs: 403.20, Fat: 42.40";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test16_integrationTestRound1_3() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 5;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	NutritionValue res = foods.getNutrition();
    	String result = res.toString();
    	String expected = "Calories: 5001.55, Protein: 26.40, Carbs: 504.35, Fat: 53.45";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test17_integrationTestRound1_4() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 5;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.getName();
    	String expected = "Fish";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test18_integrationTestRound1_5() {
    	String name = "Puffs";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 5;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.getName();
    	String expected = "Puffs";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test19_integrationTestRound1_6() {
    	String name = "Bacon";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 5;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.getName();
    	String expected = "Bacon";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test20_integrationTestRound1_7() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 5;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 5.00";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test21_integrationTestRound1_8() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.7;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 6.70";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test22_integrationTestRound1_9() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.78;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 6.78";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test23_integrationTestRound1_10() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.789;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 6.79";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test24_integrationTestRound1_11() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.789;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	foods.setQuantity(5.1);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 5.10";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test25_integrationTestRound1_12() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.789;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	foods.setQuantity(5.678);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 5.68";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test26_integrationTestRound1_13() {
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	double quantity = 6.789;
    	FoodPortion foods = new FoodPortion(food, quantity);
    	foods.setQuantity(10);
    	
    	String result = foods.toString();
    	String expected = "Name: Fish, Quantity: 10.00";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test27_integrationTestRound2_1() {
    	Exception exc = assertThrows(InvalidDateException.class, () -> {
    		Day day = new Day("2023-13-13");
    	});
    	
    	String expected = "Text '2023-13-13' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test28_integrationTestRound2_2() {
    	Exception exc = assertThrows(InvalidDateException.class, () -> {
    		Day day = new Day("2023-12-40");
    	});
    	
    	String expected = "Text '2023-12-40' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 40";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test29_integrationTestRound2_3() {
    	Exception exc = assertThrows(InvalidDateException.class, () -> {
    		Day day = new Day("2023-12-40");
    	});
    	
    	String expected = "Text '2023-12-40' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 40";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test30_integrationTestRound2_4() {
    	Exception exc = assertThrows(InvalidDateException.class, () -> {
    		Day day = new Day("1-1-1");
    	});
    	
    	String expected = "Text '1-1-1' could not be parsed at index 0";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test31_integrationTestRound2_5() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 1);
    }
    
    @Test
    public void Test32_integrationTestRound2_6() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food, 5);
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 1);
    }
    
    @Test
    public void Test33_integrationTestRound2_7() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 2);
    }
    
    @Test
    public void Test34_integrationTestRound2_8() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	day.deleteFoodPortion(name);
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 1);
    }
    
    @Test
    public void Test35_integrationTestRound2_9() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	
    	String notFound = "Not food";
    	day.deleteFoodPortion(notFound); //Food to be searched not found
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 2);
    }
    
    @Test
    public void Test36_integrationTestRound2_10() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	String name3 = "Spaghetti";
    	Food food3 = new Food(name3, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	day.deleteFoodPortion(name);
    	day.upsertFoodPortion(food3, 3);
    	
    	ArrayList<FoodPortion> result = day.getFoodPortionList();
    	
    	assertEquals(result.size(), 2);
    }
    
    @Test
    public void Test37_integrationTestRound2_11() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	String name3 = "Spaghetti";
    	Food food3 = new Food(name3, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	day.upsertFoodPortion(food3, 7);
    	
    	NutritionValue res = day.getTotalNutrition();
    	
    	String result = res.toString();
    	String expected = "Calories: 15004.65, Protein: 79.20, Carbs: 1513.05, Fat: 160.35";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test38_integrationTestRound2_12() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	String name3 = "Spaghetti";
    	Food food3 = new Food(name3, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	day.upsertFoodPortion(food3, 7);
    	day.deleteFoodPortion(name);
    	day.deleteFoodPortion(name2);
    	day.deleteFoodPortion(name3);
    	
    	NutritionValue res = day.getTotalNutrition();
    	
    	String result = res.toString();
    	String expected = "Calories: 0.00, Protein: 0.00, Carbs: 0.00, Fat: 0.00";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test39_integrationTestRound2_13() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.878;
    	double fat = 10.691;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	
    	NutritionValue res = NutritionCalculator.computePercentage(day);
    	
    	String result = res.toString();
    	String expected = "Calories: 1.50, Protein: 0.32, Carbs: 1.10, Fat: 0.41";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test40_integrationTestRound2_14() throws InvalidDateException {
    	String date = "2023-12-12";
    	Day day = new Day(date);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	String name3 = "Spaghetti";
    	Food food3 = new Food(name3, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 3);
    	day.upsertFoodPortion(food2, 5);
    	day.upsertFoodPortion(food3, 7);
    	
    	NutritionValue res = NutritionCalculator.computePercentage(day);
    	
    	String result = res.toString();
    	String expected = "Calories: 7.50, Protein: 1.58, Carbs: 5.50, Fat: 2.06";
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test41_integrationTestRound2_15() {
    	Exception exc = assertThrows(ResourceNotFoundException.class, () -> {
    		User user = User.getInstance();
    		Food search = user.getFoodType("Burger");
    	});
    	
    	String expected = "Food Burger not found";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test42_integrationTestRound2_16() {
    	Exception exc = assertThrows(ResourceNotFoundException.class, () -> {
    		User user = User.getInstance();
    		Day notFound = user.findDay("2023-12-12");
    	});
    	
    	String expected = "Day 2023-12-12 not found.";
    	String result = exc.getMessage();
    	
    	assertEquals(result, expected);
    }
    
    @Test
    public void Test43_integrationTestRound2_17() throws InvalidDateException {
    	String date = "2023-01-01";
    	Day day = new Day(date);
    	User user = User.getInstance();
    	
    	user.addDay(day);
    	
    	ArrayList<Day> result = user.getDayList();
    	
    	assertEquals(result.size(), 1);
    	user.deleteDay(day);
    }
    
    @Test
    public void Test44_integrationTestRound2_18() throws InvalidDateException, ResourceNotFoundException {
    	String date = "2023-02-01";
    	String date2 = "2023-02-02";
    	Day day = new Day(date);
    	Day day2 = new Day(date2);
    	User user = User.getInstance();
    	
    	user.addDay(day);
    	user.addDay(day2);
    	
    	String result = user.findDay(date2).toString();
    	String expected = "2023-02-02";
    	
    	assertEquals(result, expected);
    	user.deleteDay(day);
    	user.deleteDay(day2);
    }
    
    @Test
    public void Test45_integrationTestRound2_19() throws InvalidDateException, ResourceNotFoundException {
    	String date = "2023-04-03";
    	String date2 = "2023-04-04";
    	Day day = new Day(date);
    	Day day2 = new Day(date2);
    	User user = User.getInstance();
    	
    	user.addDay(day);
    	user.addDay(day2);
    	user.deleteDay(day2);
    	
    	ArrayList<Day> result = user.getDayList();
    	assertEquals(result.size(), 1);
    	user.deleteDay(day);
    }
    
    @Test
    public void Test46_integrationTestRound2_20() throws InvalidDateException, ResourceNotFoundException {
    	User user = User.getInstance();
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	user.addFoodType(food);
    	
    	ArrayList<Food> result = user.getFoodList();
    	assertEquals(result.size(), 1);
    	user.deleteFoodType(food);
    }
    
    @Test
    public void Test47_integrationTestRound2_21() throws InvalidDateException, ResourceNotFoundException {
    	User user = User.getInstance();
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.879;
    	double fat = 10.69;
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	user.addFoodType(food);
    	
    	Food search = user.getFoodType(name);
    	
    	String result = search.toString();
    	String expected = "Name: Fish, Nutrition Values: (Calories: 1000.31, Protein: 5.28, Carbs: 100.88, Fat: 10.69)";
    	
    	assertEquals(result, expected);
    	user.deleteFoodType(food);
    }
    
    @Test
    public void Test48_integrationTestRound2_22() throws InvalidDateException, ResourceNotFoundException {
    	User user = User.getInstance();
    	
    	String date = "2023-05-12";
    	String date2 = "2023-06-13";
    	String date3 = "2023-07-11";
    	Day day = new Day(date);
    	Day day2 = new Day(date2);
    	Day day3 = new Day(date3);
    	
    	String name = "Fish";
    	double cal = 1000.31;
    	double protein = 5.28;
    	double carb = 100.87;
    	double fat = 10.69;
    	
    	Food food = new Food(name, cal, protein, carb, fat);
    	
    	String name2 = "Burger";
    	Food food2 = new Food(name2, cal, protein, carb, fat);
    	
    	String name3 = "Spaghetti";
    	Food food3 = new Food(name3, cal, protein, carb, fat);
    	
    	day.upsertFoodPortion(food, 1.5);
    	day2.upsertFoodPortion(food2, 3.4);
    	day3.upsertFoodPortion(food3, 5.6);
    	user.addDay(day);
    	user.addDay(day2);
    	user.addDay(day3);
    	
    	NutritionValue res = user.getAverageNutritionValue();
    	
    	String result = res.toString();
    	String expected = "Calories: 3501.08, Protein: 18.48, Carbs: 353.05, Fat: 37.41";
    	
    	assertEquals(result, expected);
    	user.deleteDay(day);
    	user.deleteDay(day2);
    	user.deleteDay(day3);
    }
    
    @Test
    public void Test49_SystemTesting_1() {
    	String input = "quit";
    	InputStream in = new ByteArrayInputStream(input.getBytes());
    	System.setIn(in);
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(out));
    	Main.main(new String[] {});
    	
    	
    	String expected = "";
    	
    	assertEquals(expected, out.toString());
    	
    }
    
    @Test
    public void Test50_SystemTesting_2() {
    	String input = "addDay|2023-12-14\nquit";
    	InputStream in = new ByteArrayInputStream(input.getBytes());
    	System.setIn(in);
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(out));
    	Main.main(new String[] {});
    	
    	
    	String expected = "Day added. Current day is: 2023-12-14\n";
    	assertEquals(expected, out.toString());
    	
    }
    
    @Test
    public void Test51_SystemTesting_3() {
    	String input = "addDay|2002-11-12\naddDay|2002-11-15\nswitchToDay|2002-11-12\nquit";
    	InputStream in = new ByteArrayInputStream(input.getBytes());
    	System.setIn(in);
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(out));
    	Main.main(new String[] {});
    	
    	
    	String expected = "Day added. Current day is: 2002-11-12\nDay added. Current day is: 2002-11-15\nSwitched to day 2002-11-12\n";
    	assertEquals(expected, out.toString());
    	
    }
}
