package main;

//import io.InputParser;
//import main.Food;
import java.util.*;
//import io.OutputFormatter;
//import main.NutritionValue;

import calculation.NutritionCalculator;
import exceptions.DayNotSelectedException;
import exceptions.InsufficientArgumentsException;
import io.InputParser;
import io.OutputFormatter;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // String filePath = "path/to/your/input/file.txt";
        // ArrayList<Food> foods = InputParser.parseInputFoodFile(filePath);
        // OutputFormatter.printTotalNutritionValue(totalNutritionValue);
        // OutputFormatter.printAverageNutritionValue(averageNutritionValue);

        Scanner in = new Scanner(System.in);

        User user = User.getInstance(); // TODO: Use command pattern?

        Day currentDay = null;

        while (true) {
            String cmdLine = in.nextLine().trim();
            if (cmdLine.equals(""))
                continue;
            String[] cmdParts = cmdLine.split("\\|"); // commands should be like: addFoodPortion|fish|100
            String cmd = cmdParts[0];
            try {
                switch (cmd) {
                    case "addDay":
                    case "switchToDay":
                    case "importFoodTypes":
                    case "removePortion":
                        if (cmdParts.length < 2) {
                            throw new InsufficientArgumentsException();
                        }
                        break;
                    case "addPortion":
                        if (cmdParts.length < 3) {
                            throw new InsufficientArgumentsException();
                        }
                        break;
                }

                if (cmd.equals("addDay")) {
                    Day day = new Day(cmdParts[1]);
                    user.addDay(day);
                    currentDay = day;
                } else if (cmd.equals("switchToDay")) {
                    Day day = user.findDay(cmdParts[1]);
                    currentDay = day;
                } else if (cmd.equals("importFoodTypes")) {
                    String foodFilePath = cmdParts[1];
                    ArrayList<Food> foodTypes = InputParser.parseInputFoodFile(foodFilePath);
                    foodTypes.stream().forEach(food -> user.addFoodType(food));
                } else if (cmd.equals("addPortion")) {
                    if (currentDay == null)
                        throw new DayNotSelectedException();
                    String foodName = cmdParts[1];
                    double amount = Double.parseDouble(cmdParts[2]);
                    Food food = user.getFoodType(foodName);
                    currentDay.upsertFoodPortion(food, amount);
                } else if (cmd.equals("removePortion")) {
                    if (currentDay == null)
                        throw new DayNotSelectedException();
                    currentDay.deleteFoodPortion(cmdParts[1]);
                } else if (cmd.equals("listFoodTypes")) {
                    user.listFoodTypes();
                } else if (cmd.equals("listFoodPortions")) {
                    if (currentDay == null)
                        throw new DayNotSelectedException();
                    currentDay.listFoodPortions();
                } else if (cmd.equals("getTotal")) {
                    if (currentDay == null)
                        throw new DayNotSelectedException();
                    NutritionValue total = currentDay.getTotalNutrition();
                    OutputFormatter.printTotalNutritionValue(total);
                } else if (cmd.equals("quit")) {
                    in.close();
                    break;
                } else {
                    System.out.println("Unknown command.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
