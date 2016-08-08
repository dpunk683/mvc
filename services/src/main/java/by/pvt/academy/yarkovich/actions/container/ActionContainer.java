package by.pvt.academy.yarkovich.actions.container;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.actions.clients.ViewAllClientsAction;
import by.pvt.academy.yarkovich.actions.lang.SetLangAction;
import by.pvt.academy.yarkovich.actions.order.AddToOrderAction;
import by.pvt.academy.yarkovich.actions.order.ConfirmOrderAction;
import by.pvt.academy.yarkovich.actions.order.GetOrderAction;
import by.pvt.academy.yarkovich.actions.order.PersonalViewOrdersAction;
import by.pvt.academy.yarkovich.actions.order.RemoveFromOrderAction;
import by.pvt.academy.yarkovich.actions.personal.PersonalLoginAction;
import by.pvt.academy.yarkovich.actions.personal.PersonalLogoutAction;
import by.pvt.academy.yarkovich.actions.products.GetDishOfTheDayAction;
import by.pvt.academy.yarkovich.actions.products.GetNewMenuAction;

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
	private static ActionContainer instance;
	private HashMap<String, Action> container;

	private ActionContainer() {
		if (container == null) {
			container = new HashMap<String, Action>();
			container.put(ACTION_VIEW_CLIENTS, new ViewAllClientsAction());
			container.put(ACTION_LOGIN, new PersonalLoginAction());
			container.put(ACTION_LOGOUT, new PersonalLogoutAction());
			container.put(ACTION_GETDAYDISH, new GetDishOfTheDayAction());
			container.put(ACTION_WATCH_MENU, new GetNewMenuAction());
			container.put(ACTION_SET_LANG, new SetLangAction());
			container.put(ACTION_ADDTOORDER, new AddToOrderAction());
			container.put(ACTION_GETORDER, new GetOrderAction());
			container.put(ACTION_REMOVE_FROM_ORDER, new RemoveFromOrderAction());
			container.put(ACTION_CONFIRM_ORDER, new ConfirmOrderAction());
			container.put(ACTION_PESR_VIEW_ORDER, new PersonalViewOrdersAction());
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
