package actions.dishes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Action;
import by.pvt.academy.yarkovich.DishService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.entity.Dish;
import constants.PageNames;

public class GetDishOfTheDayAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Dish> dishes = DishService.getInstance().getDayDish();
        request.setAttribute(AttributeNames.DAY_DISH, dishes);
        return PageNames.DAY_DISH_PAGE;
    }

}
