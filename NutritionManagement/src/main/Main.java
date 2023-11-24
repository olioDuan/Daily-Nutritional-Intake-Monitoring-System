package main;

//import io.InputParser;
//import main.Food;
import java.util.*;
//import io.OutputFormatter;
//import main.NutritionValue;

import exceptions.DayNotSelectedException;
import exceptions.InsufficientArgumentsException;
import io.InputParser;

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
            String[] cmdParts = cmdLine.split("\\|"); //commands should be like: addFoodPortion|fish|100
            String cmd = cmdParts[0];
            try {
                switch (cmd) {
                    case "addDay":
                        if (cmdParts.length < 2) {
                            throw new InsufficientArgumentsException();
                        }
                }

                if (cmd.equals("addDay")) {
                    Day day = new Day(cmdParts[1]);
                    user.addDay(day);
                    currentDay = day;
                } else if (cmd.equals("switchToDay")) {
                    Day day = user.findDay(cmd);
                    currentDay = day;
                } else if (cmd.equals("Import food types")) {
                    String foodFilePath = cmdParts[1];
                    ArrayList<Food> foodTypes = InputParser.parseInputFoodFile(foodFilePath);
                    foodTypes.stream().forEach(food -> user.addFoodType(food));
                } else if (cmd.equals("quit")) {
                    in.close();
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
