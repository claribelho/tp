package seedu.duke.user;

import seedu.duke.Drink;
import seedu.duke.Meal;
import seedu.duke.Parser;
import seedu.duke.Water;

import java.util.ArrayList;

public class User {
    protected static ArrayList<Meal> mealList;
    protected static ArrayList<Drink> drinkList;
    private static ArrayList<Water> totalWaterIntake;

    public User() {
        mealList = new ArrayList<>();
        drinkList = new ArrayList<>();
        totalWaterIntake = new ArrayList<>();
    }

    public void handleMeal(String command) {
        Parser.parseMeal(command);
        String mealName = Parser.mealDescription;
        int servingSize = Parser.mealSize;

        mealList.add(new Meal(mealName, servingSize));
        System.out.println("Added " + servingSize + " serving of " + mealName);
    }

    public void handleDrink(String command) {
        Parser.parseDrink(command);
        String drinkName = Parser.drinkDescription;
        int servingSize = Parser.drinkSize;

        drinkList.add(new Drink(drinkName, servingSize));
        System.out.println("Added " + servingSize + " ml of " + drinkName);
    }

    public void handleWater(String command) {
        Parser.parseWater(command);
        int volume = Parser.waterSize;

        totalWaterIntake.add(new Water(volume));
        System.out.println("Added " + volume + " ml of water");
    }
    public void handleViewCalories() {
        int caloriesCount = 0;
        for (Meal meal: mealList) {
            caloriesCount += meal.getCalories();
        }
        for (Drink drink: drinkList) {
            caloriesCount += drink.getCalories();
        }
        System.out.println("Total Calories: " + caloriesCount);
    }

    public void handleViewCarbohydrates() {
        int carbohydratesCount = 0;
        for (Meal meal: mealList) {
            carbohydratesCount += meal.getCarbs();
        }
        for (Drink drink: drinkList) {
            carbohydratesCount += drink.getCarbs();
        }
        System.out.println("Total Carbohydrates: " + carbohydratesCount);
    }

    public void handleViewProteins() {
        int proteinCount = 0;
        for (Meal meal: mealList) {
            proteinCount += meal.getProtein();
        }
        for (Drink drink: drinkList) {
            proteinCount += drink.getProtein();
        }
        System.out.println("Total Proteins: " + proteinCount);
    }

    public void handleViewWaterIntake() {
        int waterIntake = 0;
        for (Water water: totalWaterIntake) {
            waterIntake += water.getWater();
        }
        System.out.println("Total water intake: " + waterIntake + " ml");
    }

    public void handleViewFiber() {
        int fibreCount = 0;
        for (Meal meal: mealList) {
            fibreCount += meal.getFiber();
        }
        System.out.println("Total Fiber: " + fibreCount);
    }

    public void handleViewFat() {
        int fatCount = 0;
        for (Meal meal: mealList) {
            fatCount += meal.getFat();
        }
        for (Drink drink: drinkList) {
            fatCount += drink.getFat();
        }
        System.out.println("Total Fat: " + fatCount);
    }

    public void handleViewSugar() {
        int sugarCount = 0;
        for (Meal meal: mealList) {
            sugarCount += meal.getSugar();
        }
        for (Drink drink: drinkList) {
            sugarCount += drink.getSugar();
        }
        System.out.println("Total Sugar: " + sugarCount);
    }

    public void printMealList(int startIndex) {
        for (int i = 0; i < mealList.size(); i++) {
            Meal currentMeal = mealList.get(i);
            System.out.print((startIndex+i) + ". " + currentMeal.getName());
        }
        System.out.println();
    }
    public void handleListMeals() {
        System.out.println("here's what you have eaten today");
        if (mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printMealList(1);
        }
    }

    public void printDrinkList(int startIndex) {
        for (int i = 0; i < drinkList.size(); i++) {
            Drink currentDrink = drinkList.get(i);
            System.out.print((startIndex+i) + ". " + currentDrink.getName());
        }
    }

    public void handleListDrinks() {
        System.out.println("here's what you have drank today");
        if (drinkList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printDrinkList(1);
            System.out.println();
            handleViewWaterIntake();
        }
    }

    public void handleListEverything() {
        System.out.println("here's what you have drank today");
        if (drinkList.isEmpty() && mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printMealList(1);
            printDrinkList(mealList.size()+1);
            System.out.println();
            handleViewWaterIntake();
        }
    }

    public static void handleEditMealServingSize(String command) {
        int slashIndex = command.indexOf("/");
        int mealIndex = Integer.parseInt(command.substring(20, slashIndex - 3));
        String mealName = mealList.get(mealIndex).getName();
        int servingSize = Integer.parseInt(command.substring(slashIndex));

        Meal updatedMeal = new Meal(mealName, servingSize);
        mealList.set(mealIndex, updatedMeal);
        System.out.println(mealName + "has been edited to " + servingSize + " serving(s)");
    }

    public static void handleEditDrinkServingSize(String command) {
        int slashIndex = command.indexOf("/");
        int drinkIndex = Integer.parseInt(command.substring(21, slashIndex - 3));
        String drinkName = mealList.get(drinkIndex).getName();
        int servingSize = Integer.parseInt(command.substring(slashIndex));

        Meal updatedDrink = new Drink(drinkName, servingSize);
        mealList.set(drinkIndex, updatedDrink);
        System.out.println(drinkName + "has been edited to " + servingSize " ml");
    }

    public void handleDeleteMeal(String command) {
        int mealIndex = Integer.parseInt(command.substring(11));
        String mealName = mealList.get(mealIndex).getName();
        mealList.remove(mealIndex);

        System.out.println("Removed " + mealName + " from Meals");
    }

    public void handleDeleteDrink(String command) {
        int drinkIndex = Integer.parseInt(command.substring(12));
        String drinkName = drinkList.get(drinkIndex).getName();
        drinkList.remove(drinkIndex);
        System.out.println("Removed " + drinkName + " from Meals");
    }

    public void handleClear() {
        mealList.clear();
        drinkList.clear();
        System.out.println("All entries have been deleted");
    }

}
