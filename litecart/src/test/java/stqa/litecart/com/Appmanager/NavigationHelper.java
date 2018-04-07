package stqa.litecart.com.Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(EventFiringWebDriver driver) {
        super(driver);
    }


    public void gotoAdminPage(){
        gotoUrl("http://localhost/litecart/admin/");
    }

    public void gotoShopMainPage(){
        gotoUrl("http://localhost/litecart/");
    }

    public void gotoCart() {
        click(By.cssSelector("div#cart"));
    }
}
