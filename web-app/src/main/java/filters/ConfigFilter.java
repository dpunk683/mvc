/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.pvt.academy.yarkovich.EmployeeService;
import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.EmployeeDAO;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.logger.RestLogger;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import constants.PageNames;

/**
 * Фильтр исполняет две задачи: кодирует параметры request объекта в UTF-8 и
 * анализирует куки в случае новой сессии.
 * <p>
 * Filter stands for two tasks: encodes request object in UTF-8 and
 * analyses cookies in case of new session.
 */
public class ConfigFilter implements Filter {

    public static String ID = "id";
    public static String LOCALE = "locale_";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //устанавливаем кодировку
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession httpSession = req.getSession();
        Integer accesslevel = (Integer) httpSession.getAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE);
        req.setCharacterEncoding("UTF-8");
        try {
            //смотрим куки
            initSession(((HttpServletRequest) req).getSession(), (HttpServletRequest) req);
        } catch (RuntimeException e) {
            RestLogger.getInstance(this.getClass()).writeError("Exception at ConfigFilter: " + e);
        }
        if (accesslevel == null) {
            accesslevel = AccessLevels.GUEST;
            httpSession.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, accesslevel);
            //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/login.jsp");
            //dispatcher.forward(req, resp);
            resp.sendRedirect(req.getContextPath() + PageNames.INDEX_PAGE);
            return;
        }
        chain.doFilter(req, resp);
    }

    private void initSession(HttpSession httpSession, HttpServletRequest request) {
        try {
            if (httpSession.isNew()) {
                Cookie[] cookies = request.getCookies();
                boolean isFound = false;
                if (cookies != null) {
                    for (Cookie c : cookies) {

                        //looking for user id (for authorize purpose)
                        if ((c.getName()).equals(ID)) {
                            Employee employee = EmployeeService.getInstance().getByID(Long.parseLong(c.getValue()));
                            httpSession.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, employee.getType());
                            httpSession.setAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE, employee);
                            isFound = true;
                        }

                        // looking for user specified locale
                        if ((c.getName()).equals(AttributeNames.SESSION_LOCALE_ATTRIBUTE)) {
                            httpSession.setAttribute(AttributeNames.SESSION_LOCALE_ATTRIBUTE, LOCALE + c.getValue());
                        }
                    }//end of foreach loop
                }//end of if(cookies != null)

                if (!isFound) {
                    httpSession.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, AccessLevels.GUEST);
                }
            }//end of if(httpSession)
        } catch (RuntimeException e) {
            RestLogger.getInstance(this.getClass()).writeError("Exception at ConfigFilter: Init Session " + e);
        }
    }


    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void destroy() {
    }
}
