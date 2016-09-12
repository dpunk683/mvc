package actions.clients;

import actions.Action;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dzmitryyarkovich on 9/11/16.
 */
public class NewClientFormAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageNames.NEW_CLIENT_PAGE;
    }
}
