package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MealInMemory implements MealDao{

    private final Map<Integer, Meal> mealMap;

    public MealInMemory() {
        mealMap = new HashMap<>();
    }

    public void addMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    public void deleteMeal(int mealId) {
        mealMap.remove(mealId);
    }

    public void updateMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealMap.values());
    }

    public Meal getMealById(int mealId) {
        return mealMap.get(mealId);
    }
}