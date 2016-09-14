package actions.clients;

import actions.Action;
import by.pvt.academy.yarkovich.ClientsService;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.EmailExistsErrorException;
import by.pvt.academy.yarkovich.exceptions.PhoneExistsErrorException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dzmitryyarkovich on 9/11/16.
 */
public class AddNewClientAction extends Action {
    private static AddNewClientAction instance;

    private AddNewClientAction() {
    }

    public synchronized static AddNewClientAction getInstance() {
        if (instance == null) {
            instance = new AddNewClientAction();
        }
        return instance;
    }

    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {
        Client client = new Client();
        client.setName(request.getParameter("fio"));
        client.setEmail(request.getParameter("email"));
        client.setPhone(request.getParameter("phone"));
        client.setDateOfBirth(request.getParameter("dayofb")+"-"+request.getParameter("monthofb")+"-"+request.getParameter("yearofb"));
        client.setLoyalityCardNo(request.getParameter("cardNum"));
        client.setSpentMoney(0);
        try {
            ClientsService.getInstance().validation(client);
            ClientsService.getInstance().addClient(client);
        } catch (CardNumErrorException e) {
            return PageNames.CLIENT_CARDNUM_EXISTS_PAGE;
        } catch (EmailExistsErrorException e) {
            return PageNames.CLIENT_EMAIL_EXISTS_PAGE;
        } catch (PhoneExistsErrorException e) {
            return PageNames.CLIENT_PHONE_EXISTS_PAGE;
        } finally {
            HibernateUtil.closeSession();
        }
        return PageNames.SUCCESS_CLIENT_ADDED_PAGE;
    }
}


