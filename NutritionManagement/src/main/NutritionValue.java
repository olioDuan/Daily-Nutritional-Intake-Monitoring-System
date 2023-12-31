package main;

public class NutritionValue {
    public NutritionValue(double calories, double protein, double carbs, double fat) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    @Override
    public String toString() {
        return String.format("Calories: %.2f, Protein: %.2f, Carbs: %.2f, Fat: %.2f", this.calories, this.protein, this.carbs,
                this.fat);
    }
}
