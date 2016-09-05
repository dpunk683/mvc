package  by.pvt.academy.yarkovich.managers;

import javax.servlet.http.HttpServletRequest;

import by.pvt.academy.yarkovich.constants.AttributeNames;
//import by.pvt.yarkovich.restaraunt.controller.GlobalContr;
import by.pvt.academy.yarkovich.logger.RestLogger;


public class ErrorManager {
    
    private static ErrorManager instance;
    
    private ErrorManager(){
    }

    public static ErrorManager getInstance() {
        if(instance == null) {
            instance = new ErrorManager();
        }
        return instance;
    }
    
    /**
     * Устанавливает значение атрибута ERR_MESSAGE объекта request и записывает
     * его в лог файл, если значение toLog есть true
     * 
     * Sets the value of ERR_MESSAGE attribute of request object and appends it
     * to log file if toLog is true
     */
    public void writeError(HttpServletRequest request, Exception e,
            String msg, boolean toLog) {

        //Setting errmessage attribute
//       ?
        //TODO
        if (true) {
            if(e == null) {
                request.setAttribute(AttributeNames.ERR_MESSAGE_ATTRIBUTE, msg);
            } else {
                request.setAttribute(AttributeNames.ERR_MESSAGE_ATTRIBUTE, msg + ":" + e);
            }
        } else {
            request.setAttribute(AttributeNames.ERR_MESSAGE_ATTRIBUTE, MessageManager.ERR_SORRY);
        }

        //Writing to log file
        if(toLog) {
            if(e == null) {
                RestLogger.getInstance(request.getClass()).writeError(msg);
            } else {
            	RestLogger.getInstance(request.getClass()).writeError(msg + ":" + e);
            }
        }
    }
}