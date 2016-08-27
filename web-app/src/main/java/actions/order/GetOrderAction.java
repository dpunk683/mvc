package actions.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import actions.Action;
import constants.PageNames;

public class GetOrderAction extends Action {
		
		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) {
	        return PageNames.ORDER_PAGE;
		}

}
