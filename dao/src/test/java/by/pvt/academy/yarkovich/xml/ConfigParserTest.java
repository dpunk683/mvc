package by.pvt.academy.yarkovich.xml;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by dima on 11.08.2016.
 */
public class ConfigParserTest extends TestCase {
    private static String CONFIG_XML = "config.xml";

    public void testGetLogin() throws Exception {
        ConfigParser configParser = new ConfigParser();
        configParser.parseConfig(CONFIG_XML);
        Assert.assertEquals("root",configParser.getLogin() );
    }

    public void testGetPassword() throws Exception {
        ConfigParser configParser = new ConfigParser();
        configParser.parseConfig(CONFIG_XML);
        Assert.assertEquals("guir45gg",configParser.getPassword().trim());
    }

    public void testGetUrl() throws Exception {
        ConfigParser configParser = new ConfigParser();
        configParser.parseConfig(CONFIG_XML);
        Assert.assertEquals("jdbc:mysql://localhost:3306/restaraunt_db",configParser.getUrl() );
    }

    public void testGetDriver() throws Exception {
        ConfigParser configParser = new ConfigParser();
        configParser.parseConfig(CONFIG_XML);
        Assert.assertEquals("com.mysql.jdbc.Driver",configParser.getDriver());
    }

}