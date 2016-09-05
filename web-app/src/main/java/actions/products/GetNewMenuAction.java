package actions.products;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Action;
import by.pvt.academy.yarkovich.ProductsService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.ProductDAO;
import by.pvt.academy.yarkovich.entity.Product;
import constants.PageNames;

public class GetNewMenuAction extends Action {

	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = ProductsService.getInstance().getNewMenu();
		request.setAttribute(AttributeNames.MENU, products);
		return PageNames.MENU_PAGE;
	}

}
