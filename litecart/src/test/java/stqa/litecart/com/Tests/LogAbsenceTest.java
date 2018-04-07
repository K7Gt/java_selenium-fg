package stqa.litecart.com.Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LogAbsenceTest extends TestBase {

    @Before
    public  void preconditions(){
        app.getNavigationHelper().gotoAdminPage();
        app.getAdminPageHelper().login();
    }

    @Test
    public void testLogAbsence(){
        app.getHelperBase().gotoUrl("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> products = app.getAdminPageHelper().productList(By.cssSelector("tr.row"));

        for (int i = 1; i <= products.size(); i++) {
            WebElement option = app.getHelperBase().getWebElement(By.cssSelector("tr.row:nth-child("+(i+4)+")"));
            option.findElement(By.cssSelector("a")).click();
            Assert.assertEquals(0, app.getHelperBase().getLogsSize());
            app.getHelperBase().click(By.cssSelector("button[name = cancel]"));
        }
    }
}
