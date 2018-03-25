package stqa.litecart.com.Tests;

import org.junit.Before;
import org.junit.Test;
import stqa.litecart.com.Model.User;

public class RegistrationLoginTest extends TestBase{

    @Before

    public void preconditions(){
        app.getNavigationHelper().gotoShopMainPage();
    }

    User newUser = new User(
            "123",
            "testCompany",
            "testName",
            "testLastname",
            "testaddress1",
            "testaddress2",
            "12345",
            "New York",
            "United States",
            "Arizona",
            "test@test.com",
            "123456789",
            false,
            "testpass"
    );

    @Test
    public void testRegistration(){

        app.getShopPageHelper().initRegistration();
        app.getShopPageHelper().fillRegistrationForm(newUser);
        app.getShopPageHelper().submitRegistration();
        app.getShopPageHelper().logOut();
        app.getShopPageHelper().loginUser(newUser);
        app.getShopPageHelper().logOut();

    }

}
