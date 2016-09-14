package actions.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Action;
import by.pvt.academy.yarkovich.DishService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.entity.Order;
import by.pvt.academy.yarkovich.entity.Dish;
import constants.PageNames;

public class RemoveFromOrderAction extends Action {
	private final String PRODUCT_ID_TO_REMOVE = "id";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute(AttributeNames.ORDER_ATTRIBUTE);
        if(order == null) {
            throw new RuntimeException("RemoveFromOrderAction: order doesnt exist");
        }
        	Dish dish = DishService.getInstance().getById(Long.parseLong(request.getParameter(PRODUCT_ID_TO_REMOVE)));
            order.removeProduct(dish);
        return PageNames.ORDER_PAGE;
	}

}
