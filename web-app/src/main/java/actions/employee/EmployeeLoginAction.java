package actions.employee;

import actions.Action;
import by.pvt.academy.yarkovich.EmployeeService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.AuthorizationErrorException;
import by.pvt.academy.yarkovich.logger.RestLogger;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import constants.PageNames;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmployeeLoginAction extends Action {
    private final String ID = "id";
    private final String LOGIN = "login";
    private final String PASSWORD = "pass";
    private final int ONE_WEEK = 60 * 60 * 24 * 7;

    private static EmployeeLoginAction instance;

    private EmployeeLoginAction() {
    }

    public synchronized static EmployeeLoginAction getInstance() {
        if (instance == null) {
            instance = new EmployeeLoginAction();
        }
        return instance;
    }

    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        Integer accesslevel = (Integer) httpSession.getAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE);
        //Если не существует такого атрибута, значит приложение работает некорректно
        if (accesslevel == null) {
            RestLogger.getInstance(this.getClass()).writeError ("Accesslevel is null");
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
            httpSession.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, employee.getType());
            httpSession.setAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE, employee);
            Cookie c = new Cookie(ID, String.valueOf(employee.getId()));
            c.setMaxAge(ONE_WEEK);
            response.addCookie(c);
            return PageNames.INDEX_PAGE;
        } catch (AuthorizationErrorException e) {
            RestLogger.getInstance(this.getClass()).writeError("Exception at EmplLoginAction: " + e);
            return PageNames.ERR_PAGE;
        }
    }
}
