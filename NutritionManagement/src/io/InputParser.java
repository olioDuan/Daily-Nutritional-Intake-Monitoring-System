package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Food;

public class InputParser {
    public static ArrayList<Food> parseInputFoodFile(String path) {
        ArrayList<Food> foodList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split(",");
                if (tokens.length == 5) {
                    String name = tokens[0].trim();
                    double calories = Double.parseDouble(tokens[1].trim());
                    double protein = Double.parseDouble(tokens[2].trim());
                    double carbs = Double.parseDouble(tokens[3].trim());
                    double fat = Double.parseDouble(tokens[4].trim());

                    Food food = new Food(name, calories, protein, carbs, fat);
                    foodList.add(food);
                } else {
                    System.out.println("Invalid food entry: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }

        return foodList;
    }
}