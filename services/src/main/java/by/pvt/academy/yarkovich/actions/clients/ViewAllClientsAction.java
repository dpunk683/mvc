package by.pvt.academy.yarkovich.actions.clients;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.pvt.academy.yarkovich.actions.Action;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.constants.PageNames;
import by.pvt.academy.yarkovich.dao.ClientDAO;
import by.pvt.academy.yarkovich.entity.Client;

public class ViewAllClientsAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Client> list = ClientDAO.getInstance().get();
		request.setAttribute(AttributeNames.SHOP_CLIENTS_ATTRIBUTE, list);
		return PageNames.CLIENTS_PAGE;
    }
}