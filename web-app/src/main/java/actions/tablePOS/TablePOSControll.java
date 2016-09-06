package actions.tablePOS;

import actions.Action;
import by.pvt.academy.yarkovich.TablePOSService;
import by.pvt.academy.yarkovich.constants.AttributeNames;
import by.pvt.academy.yarkovich.entity.TablePOS;
import constants.PageNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dima on 05.09.2016.
 */
public class TablePOSControll extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<TablePOS> tablePOS = TablePOSService.getInstance().getAll();
        request.setAttribute(AttributeNames.WHOLE_POSES, tablePOS);
        return PageNames.MENU_PAGE;
    }
}
