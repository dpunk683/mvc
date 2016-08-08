package  by.pvt.academy.yarkovich.managers;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import by.pvt.academy.yarkovich.constants.AttributeNames;

public class MessageManager {

    private static MessageManager instance;
    private static ResourceBundle bundle;
    
    public static final String BUNDLE_NAME = "locale";
    
    public static final String ERR_TITLE = "err.title";
    public static final String ERR_MALFORMED = "err.malformed";
    public static final String ERR_SORRY = "err.sorry";
    public static final String ERR_LOGIN = "err.login";
    public static final String ERR_NEED_LOGIN = "err.need.login";
    public static final String ERR_REG_MALFORMED = "err.reg.malformed";
    public static final String ERR_REG_LOGIN = "err.reg.login";
    public static final String ERR_ACCESS = "err.access";
    public static final String MALFORMED_NAME = "malformed.name";
    public static final String MALFORMED_SURNAME = "malformed.surname";
    public static final String MALFORMED_LOGIN = "malformed.login";
    public static final String MALFORMED_PASSWORD = "malformed.password";
    public static final String MALFORMED_EMAIL = "malformed.email";
    public static final String MALFORMED_PHONE = "malformed.phone";
    public static final String MALFORMED_ADDRESS = "malformed.address";
    public static final String MALFORMED_YEAR = "malformed.year";
    public static final String MALFORMED_GENRE = "malformed.genre";
    public static final String MALFORMED_COUNTRY = "malformed.country";
    public static final String MALFORMED_DURATION = "malformed.duration";
    public static final String MALFORMED_DETAILS = "malformed.details";
    public static final String MALFORMED_PRICE = "malformed.price";
    public static final String CART_ADDED = "cart.added";
    public static final String SUCCESS_ADDED = "success.added";
    public static final String SUCCESS_REG = "success.reg";
    public static final String SEARCH_RESULT_SUCCESS = "search.result.success";
    public static final String SEARCH_RESULT_FAIL = "search.result.fail";
    public static final String ORDER_ADDED = "order.added";
    public static final String ORDER_STATUS_UPDATED = "order.status.updated";
    public static final String USER_BLOCKED = "user.blocked";
    public static final String USER_NOT_BLOCKED = "user.not.blocked";
    
    private MessageManager() {
    }
    
    public static MessageManager getInstance() {
        if(instance == null) {
            instance = new MessageManager();
            bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return bundle.getString(key);
    }
    
    public void writeMessage(HttpServletRequest request, String msg) {
        request.setAttribute(AttributeNames.MESSAGE_ATTRIBUTE, msg);
    }
}