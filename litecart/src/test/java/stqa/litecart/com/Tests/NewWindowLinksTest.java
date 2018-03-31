package stqa.litecart.com.Tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewWindowLinksTest extends TestBase {

    @Before
    public void preconditions(){
        app.getNavigationHelper().gotoAdminPage();
        app.getAdminPageHelper().login();
    }

    @Test
    public void testNewWindowLinks(){
        app.getAdminPageHelper().gotoCountriesTab();
        app.getAdminPageHelper().gotoAddNewCountriesPage();
        List<WebElement> elements = app.getHelperBase().getListOfElements(By.cssSelector("i.fa.fa-external-link"));
        app.getAdminPageHelper().openAllLinks(elements);

    }
}
