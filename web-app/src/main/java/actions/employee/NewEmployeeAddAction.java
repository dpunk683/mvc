package actions.employee;

import actions.Action;
import by.pvt.academy.yarkovich.EmployeeService;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.LoginExistsErrorException;
import by.pvt.academy.yarkovich.utils.PassCoder;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dima on 04.09.2016.
 */
public class NewEmployeeAddAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("fio"));
        employee.setType(Integer.parseInt(request.getParameter("acclevel")));
        try {
            EmployeeService.getInstance().checkLoginName(request.getParameter("login"));
        } catch (LoginExistsErrorException e) {
            return PageNames.EMPLOYEE_LOGIN_EXISTS_PAGE;
        }
        employee.setLogin(request.getParameter("login"));
        employee.setPass(PassCoder.getHashCode(request.getParameter("password")));
        if (request.getParameter("cardNum") != null) {
            try {
                EmployeeService.getInstance().checkCardNum(request.getParameter("cardNum"));
            } catch (CardNumErrorException e) {
                return PageNames.EMPLOYEE_EXISTS_PAGE;
            }
            employee.setCardNum(request.getParameter("cardNum"));
        }
        EmployeeService.getInstance().addEmployee(employee);
        return PageNames.SUCCESS_PAGE;
    }
}
