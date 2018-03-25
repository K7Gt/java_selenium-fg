package stqa.litecart.com.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminMenuTest extends TestBase {


    @Test
    public void testAdminMenu(){

        app.getNavigationHelper().gotoAdminPage();
        app.getAdminPageHelper().login();

        for (int i = 1; i <= app.getHelperBase().getListOfElements(By.cssSelector("li#app-")).size(); i++) {
            WebElement option = app.getHelperBase().getWebElement(By.cssSelector("li#app-:nth-child("+i+")"));
            option.findElement(By.cssSelector("a:first-child")).click();
            Assert.assertTrue(app.getHelperBase().isElementPresent(By.cssSelector("td#content h1")));

            if(app.getHelperBase().getListOfElements(By.cssSelector("li#app-:nth-child("+i+") ul.docs li")).size() > 0) {
                for (int j = 1; j <= app.getHelperBase().getListOfElements(By.cssSelector("li#app-:nth-child("+i+") ul.docs li")).size(); j++) {
                    WebElement subOption = app.getHelperBase().getWebElement(By.cssSelector("ul.docs li:nth-child("+j+")"));
                    subOption.findElement(By.cssSelector("a:first-child")).click();
                    Assert.assertTrue(app.getHelperBase().isElementPresent(By.cssSelector("td#content h1")));
                }
            }

        }


    }


}
