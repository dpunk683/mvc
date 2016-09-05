package actions.order;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Action;
import by.pvt.academy.yarkovich.ProductsService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.ProductDAO;
import by.pvt.academy.yarkovich.entity.Order;
import by.pvt.academy.yarkovich.entity.Product;
import by.pvt.academy.yarkovich.managers.MessageManager;
import constants.PageNames;

public class AddToOrderAction extends Action {
    private final String PRODUCT_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute(AttributeNames.ORDER_ATTRIBUTE);
        if (order == null) {
            order = new Order();
            session.setAttribute(AttributeNames.ORDER_ATTRIBUTE, order);
        }
        Product product;
            product = ProductsService.getInstance().getById(Long.parseLong(request.getParameter(PRODUCT_ID)));
            order.addProduct(product);
        return PageNames.SUCCESS_PAGE;
    }

}
