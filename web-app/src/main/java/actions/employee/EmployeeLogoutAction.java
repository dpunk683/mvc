package actions.employee;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Action;
import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import constants.PageNames;

public class EmployeeLogoutAction extends Action {
    private final String USER_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, AccessLevels.GUEST);
        session.removeAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE);
        Cookie c = new Cookie(USER_ID, "");
        c.setMaxAge(0);
        response.addCookie(c);
        session.setAttribute(AttributeNames.REFRESH_ORDER_LIST, true);
        return PageNames.INDEX_PAGE;
    }
}
