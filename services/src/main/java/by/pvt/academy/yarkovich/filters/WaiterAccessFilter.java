
package by.pvt.academy.yarkovich.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.constants.PageNames;
import by.pvt.academy.yarkovich.managers.MessageManager;

/**
 * Отслеживает, есть ли у пользователя права доступа к некоторым страницам.
 * 
 * Tracks if user has rights to access certain pages.
 * 
 */
public class WaiterAccessFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer accesslevel = (Integer)session.getAttribute(AttributeNames.ACCESS_LEVEL_ATTRIBUTE);
        if((accesslevel != null) && (accesslevel.equals(AccessLevels.WAITER) || 
                accesslevel.equals(AccessLevels.ADMINISTRATOR))){
            chain.doFilter(request, response);
        } else {
            request.setAttribute(AttributeNames.ERR_MESSAGE_ATTRIBUTE, MessageManager.ERR_NEED_LOGIN);
            RequestDispatcher dispatcher = ((HttpServletRequest) request).getRequestDispatcher(PageNames.INDEX_PAGE);
            dispatcher.forward(request, response);
        }
    }


    public void init(FilterConfig filterConfig) throws ServletException {
    }
    

    public void destroy() {   
    }
}
