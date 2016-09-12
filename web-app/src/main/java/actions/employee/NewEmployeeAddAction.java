package actions.employee;

import actions.Action;
import by.pvt.academy.yarkovich.EmployeeService;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.LoginExistsErrorException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import by.pvt.academy.yarkovich.utils.PassCoder;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dima on 04.09.2016.
 */
public class NewEmployeeAddAction extends Action {

    private static NewEmployeeAddAction instance;

    private NewEmployeeAddAction() {
    }

    public synchronized static NewEmployeeAddAction getInstance() {
        if (instance == null) {
            instance = new NewEmployeeAddAction();
        }
        return instance;
    }

    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("fio"));
        employee.setType(Integer.parseInt(request.getParameter("acclevel")));
        employee.setLogin(request.getParameter("login"));
        employee.setPass(PassCoder.getHashCode(request.getParameter("password")));
        employee.setCardNum(request.getParameter("cardNum"));
        try {
            EmployeeService.getInstance().validation(employee);
            EmployeeService.getInstance().addEmployee(employee);
        } catch (CardNumErrorException e) {
            return PageNames.EMPLOYEE_EXISTS_PAGE;
        } catch (LoginExistsErrorException e) {
            return PageNames.EMPLOYEE_LOGIN_EXISTS_PAGE;
        }
        finally {
            HibernateUtil.closeSession();
        }
        return PageNames.SUCCESS_EMPLOYEE_ADDED_PAGE;
    }
}
