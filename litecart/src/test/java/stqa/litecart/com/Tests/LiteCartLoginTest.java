package stqa.litecart.com.Tests;

import org.junit.Test;


public class LiteCartLoginTest extends TestBase {

    @Test
    public void testLogin() {
        app.getNavigationHelper().gotoAdminPage();
        app.getHelperBase().login();

    }



}
