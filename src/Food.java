/**
 * Author: Taylor Ericson
 * Class: CSC-240 Computer Science II (Java)
 * Description: A class representing a food item with a name, food group, calorie
 *              count, and percent daily value.
 */

public class Food {
    private String name;
    private String group;
    private int calories;
    private double dailyPercentage;

    public Food(String name, String group, int calories, double dailyPercentage) {
        this.name = name;
        this.group = group;
        this.calories = calories;
        this.dailyPercentage = dailyPercentage;
    }

    // Getters
    public String getName() { return name; }
    public int getCalories() { return calories; }
    public double getDailyPercentage() { return dailyPercentage; }

    // Return a formatted string to look like a table listing all the foods in the list
    public String toString() {
        return String.format("%-20s %-15s %-10d %-10.2f", name, group, calories, dailyPercentage);
    }
}