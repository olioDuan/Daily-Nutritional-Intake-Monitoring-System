package main;

public class Food {
	public Food(String name, double calories, double protein, double carbs, double fat) {
		this.name = name;
		this.nutritionValue = new NutritionValue(calories, protein, carbs, fat);
	}

	private String name;

	private NutritionValue nutritionValue;

	public NutritionValue getNutritionValue() {
		return nutritionValue;
	}

	public String getName() {
		return name;
	}
}
