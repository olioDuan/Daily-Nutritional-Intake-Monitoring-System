package calculation;

import main.NutritionValue;

public class RecommendationSystem {
        /**
     * Generates a recommendation based on the user's nutrition intake.
     *
     * @param nutritionValue the user's nutrition intake
     * @return a recommendation message
     */
    public static String generateRecommendation(NutritionValue nutritionValue) {
        NutritionValue percentage = NutritionCalculator.computePercentage(nutritionValue);

        StringBuilder recommendation = new StringBuilder("Based on your nutrition intake, here are some recommendations:\n\n");

        // Recommendation thresholds (adjust as needed)
        // reference: https://www.verywellhealth.com/macronutrients-7965665#:~:text=Carbohydrates%3A%2045%E2%80%9365%25%20of,for%20children%20ages%204%E2%80%9318.
        double calorieThreshold = 0.6;
        double proteinThreshold = 0.2;
        double carbsThreshold = 0.8;
        double fatThreshold = 0.3;

        if (percentage.getCalories() > calorieThreshold) {
            recommendation.append("Reduce calorie intake.\n");
        } else if (percentage.getCalories() < calorieThreshold) {
            recommendation.append("Increase calorie intake.\n");
        }

        if (percentage.getProtein() > proteinThreshold) {
            recommendation.append("Reduce protein intake.\n");
        } else if (percentage.getProtein() < proteinThreshold) {
            recommendation.append("Increase protein intake.\n");
        }

        if (percentage.getCarbs() > carbsThreshold) {
            recommendation.append("Reduce carbohydrate intake.\n");
        } else if (percentage.getCarbs() < carbsThreshold) {
            recommendation.append("Increase carbohydrate intake.\n");
        }

        if (percentage.getFat() > fatThreshold) {
            recommendation.append("Reduce fat intake.\n");
        } else if (percentage.getFat() < fatThreshold) {
            recommendation.append("Increase fat intake.\n");
        }

        return recommendation.toString();
    }
}
