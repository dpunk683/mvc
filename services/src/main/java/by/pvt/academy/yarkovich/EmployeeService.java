package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.constants.AccessLevels;
import by.pvt.academy.yarkovich.dao.EmployeeDAO;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.AuthorizationErrorException;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.LoginExistsErrorException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by dima on 03.09.2016.
 */
public class EmployeeService {
    private static EmployeeService instance;

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public Employee checkLogin(Integer accesslevel, String login, String password) throws AuthorizationErrorException {
        //Проверяем, не пытается ли пользователь повторно авторизоваться
        if (accesslevel == AccessLevels.GUEST) {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            Transaction tx = session.beginTransaction();
            Employee employee = EmployeeDAO.getInstance().getEmployee(session, login, password);
            tx.commit();
            if (employee == null) {
                throw new AuthorizationErrorException("No such user!");
            }
            return employee;
        }
        return null;
    }

    public void checkCardNum(String cardNum) throws CardNumErrorException {
        //Ищем пользователя с такой же картой
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        List<Employee> employee = EmployeeDAO.getInstance().getEmployee(session, cardNum);
        tx.commit();
        if (employee.size() != 0) {
            throw new CardNumErrorException();
        }
    }

    public void addEmployee(Employee employee) {
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        EmployeeDAO.getInstance().addEmployee(employee, session);
        tx.commit();
    }

    public void checkLoginName(String login) throws LoginExistsErrorException {
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        List<Employee> employee = EmployeeDAO.getInstance().getEmployeeByLogin(session, login);
        tx.commit();
        if (employee.size() != 0) {
                throw new LoginExistsErrorException();
        }
    }

    public Employee getByID(int id) {
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        Employee employee = (Employee)EmployeeDAO.getInstance().get(id, session);
        tx.commit();
        return  employee;
    }
}
