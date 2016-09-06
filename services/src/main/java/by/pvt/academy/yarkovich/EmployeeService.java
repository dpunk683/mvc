package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.dao.EmployeeDAO;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.AuthorizationErrorException;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.LoginExistsErrorException;

import java.util.List;

/**
 * Created by dima on 03.09.2016.
 */
public class EmployeeService extends BaseService {
    private static EmployeeService instance;
    private static EmployeeDAO emplDAO_inst = EmployeeDAO.getInstance();

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
        if (accesslevel == AccessLevels.GUEST) {
            getTransaction().begin();
            Employee employee = emplDAO_inst.getEmployee(login, password);
            getTransaction().commit();
            if (employee == null) {
                throw new AuthorizationErrorException("No such user!");
            }
            return employee;
        }
        return null;
    }

    public synchronized void addEmployee(Employee employee) {
        getTransaction().begin();
        emplDAO_inst.addEmployee(employee);
        getTransaction().commit();
    }

    public synchronized void validation(Employee employee) throws CardNumErrorException, LoginExistsErrorException {
        getTransaction().begin();
        List<Employee> employeesSameCard = emplDAO_inst.getEmployee(employee.getCardNum());
        List<Employee> employeesSameLogin = emplDAO_inst.getEmployeeByLogin(employee.getLogin());
        getTransaction().commit();
        //Ищем пользователя с такой же картой
        if (employeesSameCard.size() != 0) {
            getTransaction().rollback();
            throw new CardNumErrorException();
        }
        //Ищем пользователя с таким логином
        if (employeesSameLogin.size() != 0) {
            getTransaction().rollback();
            throw new LoginExistsErrorException();
        }
    }

    public synchronized Employee getByID(int id) {
        getTransaction().begin();
        Employee employee = (Employee) emplDAO_inst.get(id);
        getTransaction().commit();
        return employee;
    }


}
