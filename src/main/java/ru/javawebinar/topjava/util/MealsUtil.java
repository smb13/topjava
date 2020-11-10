package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<Meal> meals = Arrays.asList(
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Admin Обед", 500),
            new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Admin Ужин", 410)
    );

    public static List<MealTo> getTosSortedByDateDesc(Collection<Meal> meals, int caloriesPerDay) {
        List<MealTo> mealsNotSorted = getTos(meals, caloriesPerDay);
        return mealsNotSorted.stream()
                .sorted(Comparator.comparing(MealTo::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
        return filterByPredicate(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredTos(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return filterByPredicate(meals, caloriesPerDay, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime));
    }

    public static List<MealTo> filterByPredicate(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(filter)
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<MealTo> tosFilterByDateAndTime(List<MealTo> mealsTo, String dateFromStr, String dateTillStr, String timeFromStr, String timeTillStr) {
        LocalDate dateFrom = !dateFromStr.equals("") ? LocalDate.parse(dateFromStr) : LocalDate.MIN;
        LocalDate dateTill = !dateTillStr.equals("") ? LocalDate.parse(dateTillStr) : LocalDate.MAX;
        LocalTime timeFrom = !timeFromStr.equals("") ? LocalTime.parse(timeFromStr) : LocalTime.MIN;
        LocalTime timeTill = !timeTillStr.equals("") ? LocalTime.parse(timeTillStr) : LocalTime.MAX;
        return mealsTo.stream()
                .filter(mealTo -> ((((
                        mealTo.getDateTime().toLocalDate().compareTo(dateFrom) >= 0)
                        && (mealTo.getDateTime().toLocalDate().compareTo(dateTill) <= 0)
                        && (mealTo.getDateTime().toLocalTime().compareTo(timeFrom) >= 0)
                        && (mealTo.getDateTime().toLocalTime().compareTo(timeTill) < 0)))))
                .collect(Collectors.toList());
    }
}
