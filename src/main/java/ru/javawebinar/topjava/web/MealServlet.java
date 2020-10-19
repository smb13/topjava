package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao dao;
    private static String INSERT_OR_EDIT = "/mealsCU.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    public MealServlet() {
        super();
        dao = new MealInMemory();
        MealsUtil.fillDb(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_MEALS;
            request.setAttribute("meals", MealsUtil.toMealTo(dao.getAllMeals(), 2000));
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = LIST_MEALS;
            request.setAttribute("meals", MealsUtil.toMealTo(dao.getAllMeals(), 2000));
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.addMeal(new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter), request.getParameter("description"), Integer.parseInt(request.getParameter("calories"))));
            ;
        } else {
            Meal meal = dao.getMealById(Integer.parseInt(id));
            meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime"), formatter));
            meal.setDescription(request.getParameter("description"));
            meal.setCalories(Integer.parseInt(request.getParameter("calories")));
            dao.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", MealsUtil.toMealTo(dao.getAllMeals(), 2000));
        view.forward(request, response);
    }
}