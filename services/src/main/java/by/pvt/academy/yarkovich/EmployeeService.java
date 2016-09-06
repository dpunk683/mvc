package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.dao.EmployeeDAO;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.AuthorizationErrorException;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.exceptions.LoginExistsErrorException;
import by.pvt.academy.yarkovich.logger.RestLogger;

import java.util.List;

import static by.pvt.academy.yarkovich.utils.HibernateUtil.*;

/**
 * Created by dima on 03.09.2016.
 */
public class EmployeeService extends BaseService {
    private static EmployeeService instance;
    private EmployeeDAO emplDAO_inst = EmployeeDAO.getInstance();

    private EmployeeService() {
    }

    public synchronized static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public synchronized Employee checkLogin(Integer accesslevel, String login, String password) throws AuthorizationErrorException {
        //Проверяем, не пытается ли пользователь повторно авторизоваться
        try {
            if (accesslevel == AccessLevels.GUEST) {
                beginTransaction();
                Employee employee = emplDAO_inst.getEmployee(login, password);
                commitTransaction();
                if (employee == null) {
                    RestLogger.getInstance(this.getClass()).writeError("AuthorizationErrorException: ");
                    throw new AuthorizationErrorException("No such user!");
                }
                return employee;
            }
        } catch (DAOException e) {
            rollbackTransaction();
        }
        return null;
    }

    public synchronized void addEmployee(Employee employee) {
        try {
            beginTransaction();
            emplDAO_inst.addEmployee(employee);
            commitTransaction();
        } catch (DAOException e) {
            RestLogger.getInstance(this.getClass()).writeError("addEmployee Service: " + e);
            rollbackTransaction();
        }
    }

    public synchronized void validation(Employee employee) throws CardNumErrorException, LoginExistsErrorException {
        try {
            beginTransaction();
            List<Employee> employeesSameCard = emplDAO_inst.getEmployee(employee.getCardNum());
            List<Employee> employeesSameLogin = emplDAO_inst.getEmployeeByLogin(employee.getLogin());
            //Ищем пользователя с такой же картой
            if (employeesSameCard.size() != 0) {
                rollbackTransaction();
                throw new CardNumErrorException();
            }
            //Ищем пользователя с таким логином
            if (employeesSameLogin.size() != 0) {
                rollbackTransaction();
                throw new LoginExistsErrorException();
            }
            commitTransaction();
        } catch (DAOException e) {
            RestLogger.getInstance(this.getClass()).writeError("validation Employee Service: " + e);
            rollbackTransaction();
        }
    }

    public synchronized Employee getByID(Long id) {
        try {
            beginTransaction();
            Employee employee = (Employee) emplDAO_inst.getEmployeeById(id);
            commitTransaction();
            return employee;
        } catch (DAOException e) {
            rollbackTransaction();
            return null;
        }
    }
}
