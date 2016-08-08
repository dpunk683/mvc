package by.pvt.academy.yarkovich.actions.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.constants.PageNames;

public class GetOrderAction extends Action {
		
		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) {
	        return PageNames.ORDER_PAGE;
		}

}
