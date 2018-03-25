package stqa.litecart.com.Tests;

import org.junit.After;
import org.junit.Before;
import stqa.litecart.com.Appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @Before
    public void setUp(){
        app.init();
    }

    @After
    public void tearDown(){
        app.stop();

    }



}
