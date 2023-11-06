package main;

public class FoodPortion {
    public FoodPortion(Food food, double quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    private Food food;
    private double quantity;

    public NutritionValue getNutrition() {
        NutritionValue unitNutritionValue = this.food.getNutritionValue();
        return new NutritionValue(
                unitNutritionValue.getCalories() * quantity,
                unitNutritionValue.getProtein() * quantity,
                unitNutritionValue.getCarbs() * quantity,
                unitNutritionValue.getFat() * quantity);
    }
}
