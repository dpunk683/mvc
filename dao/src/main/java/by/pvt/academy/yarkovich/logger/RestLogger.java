package by.pvt.academy.yarkovich.logger;

import java.io.FileOutputStream;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.xml.XMLLayout;

public class RestLogger {
	private static RestLogger instance;
    private static Logger logger;
    private static XMLLayout layout = new XMLLayout();

    public static RestLogger getInstance(Class<?> msgSender) {
        BasicConfigurator.configure();
        if (instance == null) {
            logger = Logger.getLogger(msgSender);
            WriterAppender appender = null;
            try {
                FileOutputStream output = new FileOutputStream("RestLog.xml");
                appender = new WriterAppender(layout, output);
                logger.addAppender(appender);
                instance = new RestLogger();
            } catch (Exception e) {
            }
        }
        return instance;
    }

    public void writeError(String message) {
        logger.error(message);
    }
}
