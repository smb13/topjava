package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;
import java.util.List;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;
    @Autowired
    private UserService userService;

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTosSortedByDateDesc(service.getAll(SecurityUtil.authUserId()), userService.get(SecurityUtil.authUserId()).getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(SecurityUtil.authUserId(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(SecurityUtil.authUserId(), meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(SecurityUtil.authUserId(), id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(SecurityUtil.authUserId(), meal);
    }
}
