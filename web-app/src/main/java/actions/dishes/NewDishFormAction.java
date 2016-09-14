package actions.dishes;

import actions.Action;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dzmitryyarkovich on 9/14/16.
 */
public class NewDishFormAction extends Action {
    private static NewDishFormAction instance;

    private NewDishFormAction() {
    }

    public synchronized static NewDishFormAction getInstance() {
        if (instance == null) {
            instance = new NewDishFormAction();
        }
        return instance;
    }

    @Override
    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageNames.NEW_DISH_PAGE;
    }
}