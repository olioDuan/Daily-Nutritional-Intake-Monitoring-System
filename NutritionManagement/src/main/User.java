package main;

import java.util.*;

import exceptions.ResourceNotFoundException;

public class User {
    private User() {
        days = new ArrayList<>();
        foodList = new ArrayList<>();
    }

    private static User instance = new User();

    public static User getInstance() {
        return instance;
    }

    private ArrayList<Day> days;
    private ArrayList<Food> foodList;

    public void addFoodType(Food food) {
        this.foodList.add(food);
    }

    public void deleteFoodType(Food food) {
        this.foodList.remove(food);
    }

    public Food getFoodType(String name) throws ResourceNotFoundException {
        for (Food food : foodList) {
            if (food.getName().equals(name)) {
                return food;
            }
        }
        throw new ResourceNotFoundException(
                String.format("Food %s not found", name));
    }

    public void addDay(Day day) {
        this.days.add(day);
    }

    public void deleteDay(Day day) {
        this.days.remove(day);
    }

    public Day findDay(String date) throws ResourceNotFoundException {
        return this.days.stream().filter(day -> day.getDate().toString().equals(date)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Day %s not found.", date)));
    }

    public void listFoodTypes() {
        this.foodList.stream().forEach(food -> System.out.println(food.toString()));
    }
}
