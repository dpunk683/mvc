package actions.order;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import actions.Action;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.AcceptedOrderDAO;
import by.pvt.academy.yarkovich.entity.AcceptedOrder;
import by.pvt.academy.yarkovich.entity.Order;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.entity.Product;
import by.pvt.academy.yarkovich.entity.Receipt;
import by.pvt.academy.yarkovich.managers.MessageManager;
import constants.PageNames;

public class ConfirmOrderAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Order order = (Order) (request.getSession().getAttribute(AttributeNames.ORDER_ATTRIBUTE));
		double order_sum=0;
		List<AcceptedOrder> acc_list = null;
		Receipt receipt = new Receipt();
		String ip = request.getHeader("X-FORWARDED-FOR");  
			   if (ip == null) {  
			   ip = request.getRemoteAddr();  
		   }
		List<Product> list = order.getList();
		AcceptedOrder accepted_order = new AcceptedOrder();
		AcceptedOrderDAO acceptedOrderDAO = AcceptedOrderDAO.getInstance();
		for (Product product : list) {
			accepted_order.setProductNo(product.getId());
			if (product.getSecondPrice() != 0) {
				accepted_order.setPrice(product.getSecondPrice());
				order_sum+=product.getSecondPrice();
			} else {
				accepted_order.setPrice(product.getPrice());
				order_sum+=product.getPrice();
			}
			acceptedOrderDAO.addOrder(accepted_order,ip);
		}
		receipt.setSum(order_sum);
		Employee employee = (Employee)request.getAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE);
		//ReceiptDAO.getInstance().add(receipt, employee.getId());
		order = null;
		request.getSession().setAttribute(AttributeNames.ORDER_ATTRIBUTE, order);
		accepted_order.clear();
		messageManager.writeMessage(request, MessageManager.ORDER_ADDED);
		return PageNames.SUCCESS_ORDER_PAGE;
	}
}
