package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.pvt.academy.yarkovich.managers.ErrorManager;
import by.pvt.academy.yarkovich.managers.MessageManager;

import java.io.IOException;


public abstract class Action {
    
    protected static MessageManager messageManager;
    protected static ErrorManager errorManager;
    
    protected Action() {
        messageManager = MessageManager.getInstance();
        errorManager = ErrorManager.getInstance();
    }
    
    public abstract String execute(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException;
}
