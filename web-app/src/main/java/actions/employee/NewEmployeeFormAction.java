package actions.employee;

import actions.Action;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dima on 04.09.2016.
 */
public class NewEmployeeFormAction extends Action {

    private static NewEmployeeFormAction instance;

    private NewEmployeeFormAction() {
    }

    public synchronized static NewEmployeeFormAction getInstance() {
        if (instance == null) {
            instance = new NewEmployeeFormAction();
        }
        return instance;
    }

    @Override
    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageNames.NEW_EMPLOYEE_PAGE;
    }
}
