package by.pvt.academy.yarkovich.constants;

import by.pvt.academy.yarkovich.managers.MessageManager;

public class AccessLevels {
    public static final int GUEST = 99;
    public static final int COCK = 3;
    public static final int WAITER = 2;
    public static final int ADMINISTRATOR = 1;

    public static String StatusToString(int AL) {
        switch(AL) {
            case GUEST:
                return MessageManager.USER_BLOCKED;
            case WAITER:
                return MessageManager.USER_NOT_BLOCKED;
            case ADMINISTRATOR:
                return MessageManager.USER_NOT_BLOCKED;
            default: 
                throw new IllegalArgumentException("Unknown AccessLevel");
        }
    }
}
