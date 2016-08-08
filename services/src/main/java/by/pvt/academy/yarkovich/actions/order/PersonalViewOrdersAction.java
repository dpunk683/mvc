package by.pvt.academy.yarkovich.actions.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.constants.PageNames;
import by.pvt.academy.yarkovich.dao.AcceptedOrderDAO;
import by.pvt.academy.yarkovich.entity.AcceptedOrder;
import by.pvt.academy.yarkovich.entity.Receipt;

public class PersonalViewOrdersAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		List<AcceptedOrder> acc_orders = AcceptedOrderDAO.getInstance().get();
		session.setAttribute(AttributeNames.ACC_ORDERS_ATTRIBUTE, acc_orders);   
		return PageNames.ORDERS_PAGE;
	}
}
