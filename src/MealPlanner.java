/**
 * Author: Taylor Ericson
 * Class: CSC-240 Computer Science II (Java)
 * Description: A Driver class that reads a list of food items from the file foods.txt
 *              and stores each food item using a linked list (FoodList). The user is able
 *              to view all available foods, create a meal manually, randomly generate a meal,
 *              or remove foods above a calorie limit.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MealPlanner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // scanner for user input
        FoodList foodList = new FoodList();  // create new FoodList instance

        // Load data from text file
        try {
            Scanner fileScanner = new Scanner(new File("src/foods.txt"));  // open file
            // Read each line in the text file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();  
                String[] parts = line.trim().split(" ");  // split line into parts by space

                if (parts.length == 4) {  // check for correct format
                    String name = parts[0];  // food name
                    String group = parts[1];  // food group
                    int calories = Integer.parseInt(parts[2]);  // calorie count
                    double percent = Double.parseDouble(parts[3]);  // daily percent value
                    Food food = new Food(name, group, calories, percent); // create new Food object
                    foodList.add(food); // add the food to list
                }
            }
            fileScanner.close();  // close file
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            return;
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Main menu
        boolean running = true; // Boolean for menu control
        System.out.println("\n---------------------------------");
        System.out.println("Welcome to Parkland Meal Selector");
        System.out.println("---------------------------------\n");

        while (running) {
            System.out.println("\nPlease select from the following:");
            System.out.println("1 - List food database");
            System.out.println("2 - Create meal by manual selection");
            System.out.println("3 - Create meal by random selection");
            System.out.println("4 - Remove foods high in calorie");
            System.out.println("5 - Exit");
            System.out.print("Your choice: ");
            
            int choice = scan.nextInt();
            scan.nextLine();  // consume leftover newline

            switch (choice) {
                case 1:
                    foodList.printAll();  // list all foods in database
                    break;
                case 2:
                    createManualMeal(scan, foodList);
                    break;
                case 3:
                    createRandomMeal(foodList);
                    break;
                case 4:
                    System.out.print("Enter calorie limit: ");
                    int limit = scan.nextInt();
                    scan.nextLine();  // consume new line
                    foodList.removeHighCalorieFoods(limit);  // remove calories above entered limit
                    System.out.println("Foods above " + limit + " calories have been removed.");
                    break;
                case 5:
                    running = false;  // Exit condition
                    break;
                default:
                    System.out.println("\nInvalid selection.\n");
            }
        }

        System.out.println("\nGoodbye!");
    }

    // Create a meal manually - select 3 foods from database
    private static void createManualMeal(Scanner scan, FoodList foodList) {
        Food[] meal = new Food[3];  // array to hold user selected meals
        int count = 0;

        while (count < 3) {
            System.out.print("Enter food name: ");
            String name = scan.nextLine();
            
            // check if food is in the database
            if (foodList.contains(name)) {
                meal[count] = foodList.get(name);  // add food to the meal
                count++;
            } else {
                System.out.println("Food " + name + " not in database, try again.");
            }
        }

        printMeal(meal);
    }

    // create random meal - select 3 at random from database
    private static void createRandomMeal(FoodList foodList) {
        Food[] meal = new Food[3];  // array to hold 3 foods in the meal
        for (int i = 0; i < 3; i++) {
            meal[i] = foodList.getRandom();  // select random food
        }
        printMeal(meal);
    }

    // print method for both manual and randomly created meals
    private static void printMeal(Food[] meal) {
        int totalCalories = 0;
        double totalPercent = 0.0;

        System.out.println("\n===============================");
        System.out.println("Your selected meal\n");

        System.out.print("Foods: ");
        for (Food food : meal) {
            System.out.print(food.getName() + " ");
            totalCalories += food.getCalories(); 
            totalPercent += food.getDailyPercentage();
        }

        System.out.println("\nTotal calorie count: " + totalCalories);
        System.out.printf("Total daily percentage: %.0f%%\n", totalPercent * 100);
        System.out.println("===============================\n");
    }
}