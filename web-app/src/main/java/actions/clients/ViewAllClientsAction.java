package actions.clients;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Action;
import by.pvt.academy.yarkovich.ClientsService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.dao.ClientDAO;
import by.pvt.academy.yarkovich.entity.Client;
import constants.PageNames;

public class ViewAllClientsAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Client> list = ClientsService.getInstance().getAll();
		request.setAttribute(AttributeNames.SHOP_CLIENTS_ATTRIBUTE, list);
		return PageNames.CLIENTS_PAGE;
    }
}