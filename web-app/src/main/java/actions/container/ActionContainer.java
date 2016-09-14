package actions.container;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import actions.clients.AddNewClientAction;
import actions.clients.ViewAllClientsAction;
import actions.dishes.AddNewDishAction;
import actions.dishes.NewDishFormAction;
import actions.employee.NewEmployeeAddAction;
import actions.employee.NewEmployeeFormAction;
import actions.lang.SetLangAction;
import actions.order.AddToOrderAction;
import actions.order.ConfirmOrderAction;
import actions.order.GetOrderAction;
import actions.order.EmployeeViewOrdersAction;
import actions.order.RemoveFromOrderAction;
import actions.employee.EmployeeLoginAction;
import actions.employee.EmployeeLogoutAction;
import actions.dishes.GetDishOfTheDayAction;
import actions.dishes.GetNewMenuAction;

public class ActionContainer {
    /* Possible values of action names */

    private final String ACTION_VIEW_CLIENTS = "viewclients";
    private final String ACTION_LOGIN = "login";
    private final String ACTION_LOGOUT = "logout";
    private final String ACTION_GETDAYDISH = "getdaydish";
    private final String ACTION_WATCH_MENU = "watchmenu";
    private final String ACTION_SET_LANG = "lang";
    private final String ACTION_ADDTOORDER = "addtoorder";
    private final String ACTION_GETORDER = "getorder";
    private final String ACTION_REMOVE_FROM_ORDER = "removefromorder";
    private final String ACTION_CONFIRM_ORDER = "confirmorder";
    private final String ACTION_PESR_VIEW_ORDER = "vieworders";
    private final String ACTION_REMOVE_FROM_ACCORDER = "removefromaccorder";
    private final String ACTION_ADD_EMPLOYEE = "addemployee";
    private final String ACTION_NEW_EMPLOYEE_OPEN_FORM = "newemployee";
    private final String ACTION_NEW_CLIENT_OPEN_FORM = "newclientform";
    private final String ACTION_ADD_CLIENT = "addclient";
    private final String ACTION_NEW_DISH_OPEN_FORM = "newdishform";
    private final String ACTION_ADD_DISH = "adddish";
    private static ActionContainer instance;
    private HashMap<String, Action> container;

    private ActionContainer() {
        if (container == null) {
            container = new HashMap<String, Action>();
            container.put(ACTION_VIEW_CLIENTS, new ViewAllClientsAction());
            container.put(ACTION_LOGIN, EmployeeLoginAction.getInstance());
            container.put(ACTION_LOGOUT, new EmployeeLogoutAction());
            container.put(ACTION_GETDAYDISH, new GetDishOfTheDayAction());
            container.put(ACTION_WATCH_MENU, new GetNewMenuAction());
            container.put(ACTION_SET_LANG, new SetLangAction());
            container.put(ACTION_ADDTOORDER, new AddToOrderAction());
            container.put(ACTION_GETORDER, new GetOrderAction());
            container.put(ACTION_REMOVE_FROM_ORDER, new RemoveFromOrderAction());
            container.put(ACTION_CONFIRM_ORDER, new ConfirmOrderAction());
            container.put(ACTION_PESR_VIEW_ORDER, new EmployeeViewOrdersAction());
            container.put(ACTION_NEW_EMPLOYEE_OPEN_FORM, NewEmployeeFormAction.getInstance());
            container.put(ACTION_ADD_EMPLOYEE, NewEmployeeAddAction.getInstance());
            container.put(ACTION_ADD_CLIENT, AddNewClientAction.getInstance());
            container.put(ACTION_NEW_DISH_OPEN_FORM, NewDishFormAction.getInstance());
            container.put(ACTION_ADD_DISH, AddNewDishAction.getInstance());
        }
    }

    public static ActionContainer getInstance() {
        if (instance == null) {
            instance = new ActionContainer();
        }
        return instance;
    }

    public Action getAction(HttpServletRequest request) throws ActionNotFoundException {
        String actionName = request.getParameter("an");// 'an' means action name

        if (actionName != null) {
            Action action = container.get(actionName);
            if (action == null) {
                throw new ActionNotFoundException(actionName);
            } else {
                return action;
            }
        }

        throw new IllegalArgumentException("Empty an parameter");
    }
}
