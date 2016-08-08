package by.pvt.academy.yarkovich.actions.lang;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.constants.AttributeNames;

public class SetLangAction extends Action {

	    private final String BASENAME = "locale_";
	    //Supported languages
	    private final String ENGLISH = "en_US";
	    private final String RUSSIAN = "ru_RU";
	    //Cookies store duration
	    private final int ONE_WEEK = 60*60*24*7;
	    
	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) {
	        String lang = request.getParameter("av");//'av' means action value
	        HttpSession session = request.getSession();
	        session.setAttribute(AttributeNames.SESSION_LOCALE_ATTRIBUTE,
	                BASENAME + lang);
	        Cookie c = new Cookie(AttributeNames.SESSION_LOCALE_ATTRIBUTE, lang);
	        c.setMaxAge(ONE_WEEK);
	        response.addCookie(c);
	        return null;
	    }
}
