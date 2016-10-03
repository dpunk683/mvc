package actions.dishes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Action;
import by.pvt.academy.yarkovich.DishService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.entity.Dish;
import constants.PageNames;

public class GetNewMenuAction extends Action {

	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) {
		int pageNumber=1;
		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		if(request.getParameter("page") != null) {
			httpSession.setAttribute("page", request.getParameter("page"));
			pageNumber = Integer.parseInt(request.getParameter("page"));
		} else {
			httpSession.setAttribute("page", "1");
		}
		String nextPage = (pageNumber +1) + "";
		String myUrl = "pages/menu.jsp?page=" + nextPage;
		System.out.println(myUrl);
		request.setAttribute(AttributeNames.PAGE, myUrl);
		request.setAttribute(AttributeNames.MENU, DishService.getInstance().getNewMenu(pageNumber));
		return PageNames.MENU_PAGE;
	}

}
