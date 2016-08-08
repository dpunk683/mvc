package by.pvt.academy.yarkovich.actions.personal;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.constants.PageNames;
import by.pvt.academy.yarkovich.dao.PersonalDAO;
import by.pvt.academy.yarkovich.entity.Personal;
import by.pvt.academy.yarkovich.managers.MessageManager;

public class PersonalLoginAction extends Action {
	    private final String ID = "id";
	    private final String LOGIN = "login";
	    private final String PASSWORD = "pass";
	    private final int ONE_WEEK = 60*60*24*7;

	    public String execute(HttpServletRequest request, HttpServletResponse response) {
	    	
	        HttpSession session = request.getSession();

	        Integer accesslevel = (Integer) session.getAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE);
	        //Если не существует такого атрибута, значит приложение работает некорректно
	        if (accesslevel == null) {
	            errorManager.writeError(request, null, "Accesslevel is null", true);
	            System.out.println("Login acc level 0");
	            return PageNames.ERR_PAGE;
	        }

	        //Иначе проверяем, не пытается ли пользователь повторно авторизоваться
	        if (accesslevel == AccessLevels.GUEST) {
	            try {
	                Personal personal = PersonalDAO.getInstance().getWaiter(request.getParameter(LOGIN),
	                        request.getParameter(PASSWORD));
	                if (personal == null) {
	                    errorManager.writeError(request, null, MessageManager.ERR_LOGIN, false);        
	                    return PageNames.ERR_PAGE;
	                }
	                
	                //Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты
	                //и устанавливаем куки
	                session.setAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE, personal.getType());
	                session.setAttribute(AttributeNames.USER_OBJECT_ATTRIBUTE, personal);
	                Cookie c = new Cookie(ID, String.valueOf(personal.getId()));
	                c.setMaxAge(ONE_WEEK);
	                response.addCookie(c);
	                return PageNames.INDEX_PAGE;
	                
	            } catch (SQLException e) {
	                errorManager.writeError(request, e, "SqlException at LoginUserAction", true);
	                return PageNames.ERR_PAGE;
	            }
	        }
	        return null;
	    }
}
