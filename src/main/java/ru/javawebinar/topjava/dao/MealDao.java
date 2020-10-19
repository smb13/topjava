package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface MealDao {
    public void addMeal(Meal meal);
    public void deleteMeal(int mealId);
    public void updateMeal(Meal meal) ;
    public List<Meal> getAllMeals();
    public Meal getMealById(int mealId);
}
