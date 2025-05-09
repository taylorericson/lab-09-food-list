/**
 * Author: Taylor Ericson
 * Class: CSC-240 Computer Science II (Java)
 * Description: A custom linked list class that stores Food objects.
 *              Allows for methods to add and access foods in the list.
 */

public class FoodList {
	// head of the linked list
    private FoodNode list;

    // Constructor - initialize empty list
    public FoodList() {
        list = null;
    }

    // add new food to the linked list
    public void add(Food food) {
        FoodNode node = new FoodNode(food);  // create new node
        if (list == null)  // check for empty list
            list = node;
        else {
        	// traverse list and add new node
            FoodNode current = list;
            while (current.next != null)
                current = current.next;
            current.next = node;
        }
    }

    // check if a food item is in the list
    public boolean contains(String foodName) {
        FoodNode current = list;
        while (current != null) {
            if (current.food.getName().equalsIgnoreCase(foodName))
                return true;
            current = current.next;
        }
        return false;  // not found
    }

    // get food item by name
    public Food get(String foodName) {
        FoodNode current = list;
        while (current != null) {
            if (current.food.getName().equalsIgnoreCase(foodName))
                return current.food;
            current = current.next;
        }
        return null;  // not found
    }

    // helper function - number of foods in list
    public int size() {
        int count = 0;
        FoodNode current = list;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // get a random food from the list
    public Food getRandom() {
        int index = (int) (Math.random() * size());
        FoodNode current = list;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.food;
    }

    public void removeHighCalorieFoods(int calorieLimit) {
        // check for empty list
        if (list == null) {
            return;
        }

        // Remove nodes exceeding calorie limit
        while (list != null && list.food.getCalories() > calorieLimit) {
            list = list.next;
        }

        FoodNode current = list;
        while (current != null && current.next != null) {
            if (current.next.food.getCalories() > calorieLimit) {
                current.next = current.next.next; // skip
            } else {
                current = current.next;
            }
        }
    }

    // print all the foods in the list formatted to look like a table
    public void printAll() {
        System.out.printf("\n%-20s %-15s %-10s %-10s\n", "Name", "Food Group", "Calories", "Daily %");
        System.out.println("====================================================================");
        FoodNode current = list;
        while (current != null) {
            System.out.println(current.food);
            current = current.next;
        }
    }

    // inner class representing a node in a linked list
    private class FoodNode {
        public Food food;  // food item
        public FoodNode next;  // pointer to next node in the list

        public FoodNode(Food food) {
            this.food = food;
            this.next = null;
        }
    }
}