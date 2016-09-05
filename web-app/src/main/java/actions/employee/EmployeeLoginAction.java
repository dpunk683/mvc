package actions.employee;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.Action;
import by.pvt.academy.yarkovich.EmployeeService;
import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.EmployeeDAO;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.AuthorizationErrorException;
import by.pvt.academy.yarkovich.managers.MessageManager;
import constants.PageNames;

public class EmployeeLoginAction extends Action {
    private final String ID = "id";
    private final String LOGIN = "login";
    private final String PASSWORD = "pass";
    private final int ONE_WEEK = 60 * 60 * 24 * 7;

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Integer accesslevel = (Integer) session.getAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE);
        //Если не существует такого атрибута, значит приложение работает некорректно
        if (accesslevel == null) {
            errorManager.writeError(request, null, "Accesslevel is null", true);
            System.out.println("Login acc level 0");
            return PageNames.ERR_PAGE;
        }
        try {
            Employee employee = EmployeeService.getInstance().checkLogin(accesslevel, request.getParameter(LOGIN),
                    request.getParameter(PASSWORD));
            //Если employee не найден, то уровень доступа гость
            if (employee == null) {
                return null;
            }
            //Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты
            //и устанавливаем куки
            session.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, employee.getType());
            session.setAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE, employee);
            Cookie c = new Cookie(ID, String.valueOf(employee.getId()));
            c.setMaxAge(ONE_WEEK);
            response.addCookie(c);
            return PageNames.INDEX_PAGE;
        } catch (AuthorizationErrorException e) {
            errorManager.writeError(request, null, MessageManager.ERR_LOGIN, false);
            return PageNames.ERR_PAGE;
        }
    }
}
